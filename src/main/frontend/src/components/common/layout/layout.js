import jQuery from 'jquery';
import slimScroll from 'jquery-slimscroll';

/**
 * Layout js function
 */
export default class Layout {
  constructor() {
    this.isRTL = false;
    this.isIE8 = false;
    this.isIE9 = false;
    this.isIE10 = false;
    this.resizeHandlers = [];
    this.assetsPath = '/assets/';
    this.globalImgPath = 'images/';
    this.resBreakpointMd = this._getResponsiveBreakpoint('md');
  }

  /**
   *  Initialize the layout
   */
  init() {
    this._handleInit();
    this._handleOnResize();
    this._initSlimScroll('.scroller');
    this._addResizeHandler(this._handleHeight);
    this.initHeader();
    this.initSidebar();
    this.initContent();
    this.initFooter();
  }

  /**
   *  Main function to initiate core javascript after ajax complete
   */
  initAjax() {
    this._initSlimScroll('.scroller');
  }

  /**
   *  Initialize the header
   */
  initHeader() {
     this._handleHeader();
  }

  /**
   * Set sidebar menu active link
   */
  setSidebarMenuActiveLink(mode, el, url) {
    this._handleSidebarMenuActiveLink(mode, el, url);
  }

  /**
   * Initialize the sidebar
   */
  initSidebar() {
    this._handleFixedSidebar(); // handles fixed sidebar menu
    this._handleSidebarMenu(); // handles main menu
    this._handleSidebarToggler(); // handles sidebar hide/show

    this._addResizeHandler(this._handleFixedSidebar); // reinitialize fixed sidebar on window resize
  }

  /**
   * Initialize the content
   */
  initContent() {
    this._handle100HeightContent(); // handles 100% height elements(block, portlet, etc)
    this._handleTabs(); // handle bootstrah tabs

    this._addResizeHandler(this._handleSidebarAndContentHeight); // recalculate sidebar & content height on window resize
    this._addResizeHandler(this._handle100HeightContent); // reinitialize content height on window resize
  }

  /**
   * Initialize the footer
   */
  initFooter() {
    this._handleGoTop(); //handles scroll to top functionality in the footer
  }

  /* public function to fix the sidebar and content height accordingly */
  fixContentHeight() {
    this._handleSidebarAndContentHeight();
  }

  /**
   * Init fixed sidebar hover
   */
  initFixedSidebarHoverEffect() {
    this._handleFixedSidebarHoverEffect();
  }

  /**
   * Init fixed sidebar
   */
  initFixedSidebar() {
    this._handleFixedSidebar();
  }

  /* Hanlde 100% height elements(block, portlet, etc) */
  _handle100HeightContent(context) {
    let _this = this;
    if (this === undefined && context) {
      _this = context;
    }

    jQuery('.full-height-content').each((index, elemenet) => {
      let target = jQuery(elemenet);
      let height;

      height = _this._getViewPort().height - jQuery('.page-header').outerHeight(true) - Query('.page-footer').outerHeight(true) - jQuery('.page-title').outerHeight(true) - jQuery('.page-bar').outerHeight(true);

      if (target.hasClass('portlet')) {
        let portletBody = target.find('.portlet-body');

        _this._destroySlimScroll(portletBody.find('.full-height-content-body')); // destroy slimscroll

        height = height - target.find('.portlet-title').outerHeight(true) - parseInt(target.find('.portlet-body').css('padding-top')) - parseInt(target.find('.portlet-body').css('padding-bottom')) - 2;
        if (_this._getViewPort().width >= _this.resBreakpointMd && target.hasClass("full-height-content-scrollable")) {
          height = height - 35;
          portletBody.find('.full-height-content-body').css('height', height);
          _this._initSlimScroll(portletBody.find('.full-height-content-body'));
        } else {
          portletBody.css('min-height', height);
        }
      } else {
        _this._destroySlimScroll(target.find('.full-height-content-body')); // destroy slimscroll

        if (_this._getViewPort().width >= _this.resBreakpointMd && target.hasClass("full-height-content-scrollable")) {
          height = height - 35;
          target.find('.full-height-content-body').css('height', height);
          _this._initSlimScroll(target.find('.full-height-content-body'));
        } else {
          target.css('min-height', height);
        }
      }
    });
  }

  /* Set proper height for sidebar and content. The content and sidebar height must be synced always */
  _handleSidebarAndContentHeight(context) {
    let _this = this;
    if (this === undefined && context) {
      _this = context;
    }

    let content = jQuery('.page-content');
    let sidebar = jQuery('.page-sidebar');
    let layout = jQuery('#layout');
    let height = 0;

    if (layout.hasClass("page-footer-fixed") === true && layout.hasClass("page-sidebar-fixed") === false) {
      let available_height = _this._getViewPort().height - jQuery('.page-footer').outerHeight() - jQuery('.page-header').outerHeight();
      if (content.height() < available_height) {
        content.attr('style', 'min-height:' + available_height + 'px');
      }
    } else {
      if (layout.hasClass('page-sidebar-fixed')) {
        height = _this._calculateFixedSidebarViewportHeight();
        if (layout.hasClass('page-footer-fixed') === false) {
          height = height - jQuery('.page-footer').outerHeight();
        }
      } else {
        let headerHeight = jQuery('.page-header').outerHeight();
        let footerHeight = jQuery('.page-footer').outerHeight();

        if (_this._getViewPort().width < _this.resBreakpointMd) {
          height = _this._getViewPort().height - headerHeight - footerHeight;
        } else {
          height = sidebar.height() + 20;
        }

        if ((height + headerHeight + footerHeight) <= _this._getViewPort().height) {
          height = _this._getViewPort().height - headerHeight - footerHeight;
        }
      }
      content.attr('style', 'min-height:' + height + 'px');
    }
  }

  /* Handle sidebar menu links */
  _handleSidebarMenuActiveLink(mode, el, url) {
    let menu = jQuery('.page-sidebar-menu');

    if (mode === 'click' || mode === 'set') {
      el = jQuery(el);
    } else if (mode === 'match') {
      menu.find("li > a").each((index, element) => {
        let path = jQuery(element).attr("href").toLowerCase();
        // url match condition
        if (path.length > 1 && url.substr(1, path.length - 1) == path.substr(1)) {
            el = jQuery(element);
            return;
        }
      });
    }

    if (!el || el.size() == 0) {
      return;
    }

    if (el.attr('href').toLowerCase() === 'javascript:;' || el.attr('href').toLowerCase() === '#') {
      return;
    }

    let slideSpeed = parseInt(menu.data("slide-speed"));
    let keepExpand = menu.data("keep-expanded");

    // disable active states
    menu.find('li.active').removeClass('active');
    menu.find('li > a > .selected').remove();

    if (menu.hasClass('page-sidebar-menu-hover-submenu') === false) {
      menu.find('li.open').each((index, element) => {
        if (jQuery(element).children('.sub-menu').size() === 0) {
            jQuery(element).removeClass('open');
            jQuery(element).find('> a > .arrow.open').removeClass('open');
        }
      });
    } else {
       menu.find('li.open').removeClass('open');
    }

    el.parents('li').each((index, element) => {
      jQuery(element).addClass('active');
      jQuery(element).find('> a > span.arrow').addClass('open');

      if (jQuery(element).parent('ul.page-sidebar-menu').size() === 1) {
        jQuery(element).find('> a').append('<span class="selected"></span>');
      }

      if (jQuery(element).children('ul.sub-menu').size() === 1) {
        jQuery(element).addClass('open');
      }
    });

    if (mode === 'click') {
      if (this._getViewPort().width < this.resBreakpointMd && jQuery('.page-sidebar').hasClass("in")) { // close the menu on mobile view while laoding a page
        jQuery('.page-header .responsive-toggler').click();
      }
    }
  }

  /* Handle sidebar menu */
  _handleSidebarMenu() {
    // handle sidebar link click
    jQuery('.page-sidebar-menu').on('click', 'li > a.nav-toggle, li > a > span.nav-toggle', (e) => {
      let that = jQuery(e.target).closest('.nav-item').children('.nav-link');

      if (this._getViewPort().width >= this.resBreakpointMd && !jQuery('.page-sidebar-menu').attr("data-initialized") && jQuery('#layout').hasClass('page-sidebar-closed') &&  that.parent('li').parent('.page-sidebar-menu').size() === 1) {
        return;
      }

      let hasSubMenu = that.next().hasClass('sub-menu');

      if (this._getViewPort().width >= this.resBreakpointMd && that.parents('.page-sidebar-menu-hover-submenu').size() === 1) { // exit of hover sidebar menu
        return;
      }

      if (hasSubMenu === false) {
        if (this._getViewPort().width < this.resBreakpointMd && jQuery('.page-sidebar').hasClass("in")) { // close the menu on mobile view while laoding a page
          jQuery('.page-header .responsive-toggler').click();
        }
        return;
      }

      if (that.next().hasClass('sub-menu always-open')) {
        return;
      }

      let parent = that.parent().parent();
      let the = that;
      let menu = jQuery('.page-sidebar-menu');
      let sub = that.next();

      let autoScroll = menu.data("auto-scroll");
      let slideSpeed = parseInt(menu.data("slide-speed"));
      let keepExpand = menu.data("keep-expanded");

      if (keepExpand !== true) {
        parent.children('li.open').children('a').children('.arrow').removeClass('open');
        parent.children('li.open').children('.sub-menu:not(.always-open)').slideUp(slideSpeed);
        parent.children('li.open').removeClass('open');
      }

      let slideOffeset = -200;

      if (sub.is(":visible")) {
        jQuery('.arrow', the).removeClass("open");
        the.parent().removeClass("open");
        sub.slideUp(slideSpeed, () => {
          if (autoScroll === true && jQuery('#layout').hasClass('page-sidebar-closed') === false) {
            if (jQuery('#layout').hasClass('page-sidebar-fixed')) {
              menu.slimScroll({
                'scrollTo': (the.position()).top
              });
            } else {
              this._scrollTo(the, slideOffeset);
            }
          }
          this._handleSidebarAndContentHeight();
        });
      } else if (hasSubMenu) {
        jQuery('.arrow', the).addClass("open");
        the.parent().addClass("open");
        sub.slideDown(slideSpeed, () => {
          if (autoScroll === true && jQuery('#layout').hasClass('page-sidebar-closed') === false) {
            if (jQuery('#layout').hasClass('page-sidebar-fixed')) {
              menu.slimScroll({
                  'scrollTo': (the.position()).top
              });
            } else {
              this._scrollTo(the, slideOffeset);
            }
          }
          this._handleSidebarAndContentHeight();
        });
      }
      e.preventDefault();
    });

    // handle scrolling to top on responsive menu toggler click when header is fixed for mobile view
    jQuery(document).on('click', '.page-header-fixed-mobile .page-header .responsive-toggler', () => {
      this._scrollTop();
    });

    // handle sidebar hover effect
    this._handleFixedSidebarHoverEffect();

    // handle close on body click
    if (jQuery('.sidebar-search').size() !== 0) {
      jQuery('.sidebar-search .input-group').on('click', (e) => {
        e.stopPropagation();
      });

      jQuery('#layout').on('click', () => {
        if (jQuery('.sidebar-search').hasClass('open')) {
          jQuery('.sidebar-search').removeClass("open");
        }
      });
    }
  }

  /* Handles fixed sidebar */
  _handleFixedSidebar(context) {
    let _this = this;
    if (this === undefined && context) {
      _this = context;
    }

    let menu = jQuery('.page-sidebar-menu');
    _this._destroySlimScroll(menu);

    if (jQuery('.page-sidebar-fixed').size() === 0) {
      _this._handleSidebarAndContentHeight();
      return;
    }

    if (_this._getViewPort().width >= _this.resBreakpointMd) {
      menu.attr("data-height", _this._calculateFixedSidebarViewportHeight());
      _this._initSlimScroll(menu);
      _this._handleSidebarAndContentHeight();
    }
  }

  // Handles Bootstrap Tabs.
  _handleTabs() {
    // fix content height on tab click
    jQuery('#layout').on('shown.bs.tab', 'a[data-toggle="tab"]', () => {
        this._handleSidebarAndContentHeight();
    });
  }

  /* Handles the go to top button at the footer */
  _handleGoTop() {
    let offset = 300;
    let duration = 500;

    if (navigator.userAgent.match(/iPhone|iPad|iPod/i)) {  // ios supported
      jQuery(window).bind("touchend touchcancel touchleave", (e) => {
       if (jQuery(e.target).scrollTop() > offset) {
          jQuery('.scroll-to-top').fadeIn(duration);
        } else {
          jQuery('.scroll-to-top').fadeOut(duration);
        }
      });
    } else {  // general
      jQuery(window).scroll((e) => {
        if (jQuery(e.target).scrollTop() > offset) {
          jQuery('.scroll-to-top').fadeIn(duration);
        } else {
          jQuery('.scroll-to-top').fadeOut(duration);
        }
      });
    }

    jQuery('.scroll-to-top').click((e) => {
      e.preventDefault();
      jQuery('html, body').animate({scrollTop: 0}, duration);
      return false;
    });
  }

  /* Hanles sidebar toggler */
  _handleSidebarToggler() {
    let layout = jQuery('#layout');
    if (jQuery.cookie && jQuery.cookie('sidebar_closed') === '1' && this._getViewPort().width >= this.resBreakpointMd) {
      jQuery('#layout').addClass('page-sidebar-closed');
      jQuery('.page-sidebar-menu').addClass('page-sidebar-menu-closed');
    }

    // handle sidebar show/hide
    jQuery('#layout').on('click', '.sidebar-toggler', (e) => {
      let sidebar = jQuery('.page-sidebar');
      let sidebarMenu = jQuery('.page-sidebar-menu');
      jQuery(".sidebar-search", sidebar).removeClass("open");

      if (layout.hasClass("page-sidebar-closed")) {
        layout.removeClass("page-sidebar-closed");
        sidebarMenu.removeClass("page-sidebar-menu-closed");
        if (jQuery.cookie) {
          jQuery.cookie('sidebar_closed', '0');
        }
      } else {
        layout.addClass("page-sidebar-closed");
        sidebarMenu.addClass("page-sidebar-menu-closed");
        if (layout.hasClass("page-sidebar-fixed")) {
          sidebarMenu.trigger("mouseleave");
        }
        if (jQuery.cookie) {
          jQuery.cookie('sidebar_closed', '1');
        }
      }

      jQuery(window).trigger('resize');
    });
  }

  /* Handles sidebar toggler to close/hide the sidebar */
  _handleFixedSidebarHoverEffect() {
    let layout = jQuery('#layout');
    if (layout.hasClass('page-sidebar-fixed')) {
      jQuery('.page-sidebar').on('mouseenter', (e) => {
        if (layout.hasClass('page-sidebar-closed')) {
          jQuery(e.target).find('.page-sidebar-menu').removeClass('page-sidebar-menu-closed');
        }
      }).on('mouseleave', (e) => {
        if (layout.hasClass('page-sidebar-closed')) {
          jQuery(e.target).find('.page-sidebar-menu').addClass('page-sidebar-menu-closed');
        }
      });
    }
  }

  /*Helper function to calculate sidebar height for fixed sidebar layout */
  _calculateFixedSidebarViewportHeight() {
    let sidebarHeight = this._getViewPort().height - jQuery('.page-header').outerHeight();
    if (jQuery('#layout').hasClass("page-footer-fixed")) {
      sidebarHeight = sidebarHeight - jQuery('.page-footer').outerHeight();
    }

    return sidebarHeight;
  }

  /* Handles the horizontal menu */
  _handleHeader() {
    // handle search box expand/collapse
    jQuery('.page-header').on('click', '.search-form', (e) => {
      jQuery(e.target).addClass("open");
      jQuery(e.target).find('.form-control').focus();

      jQuery('.page-header .search-form .form-control').on('blur', (ev) => {
        jQuery(ev.target).closest('.search-form').removeClass("open");
        jQuery(ev.target).unbind("blur");
      });
    });

    // handle hor menu search form on enter press
    jQuery('.page-header').on('keypress', '.hor-menu .search-form .form-control', (e) => {
      if (e.which == 13) {
        jQuery(e.target).closest('.search-form').submit();
        return false;
      }
    });

    // handle header search button click
    jQuery('.page-header').on('mousedown', '.search-form.open .submit', (e) => {
      e.preventDefault();
      e.stopPropagation();
      jQuery(e.target).closest('.search-form').submit();
    });
  }

  /* Get responsive breakpoints */
  _getResponsiveBreakpoint(size) {
    // bootstrap responsive breakpoints
    let sizes = {
      'xs' : 540,     // extra small
      'sm' : 768,     // small
      'md' : 992,     // medium
      'lg' : 1200     // large
    };

    return sizes[size] ? sizes[size] : 0;
  }

  /* Add callback a function which will be called on window resize */
  _addResizeHandler(func) {
    this.resizeHandlers.push(func);
  }

  /* Scroll(focus) to an element */
  _scrollTo(el, offeset) {
    let pos = (el && el.size() > 0) ? el.offset().top : 0;

    if (el) {
      if (jQuery('#layout').hasClass('page-header-fixed')) {
        pos = pos - jQuery('.page-header').height();
      } else if (jQuery('#layout').hasClass('page-header-top-fixed')) {
        pos = pos - jQuery('.page-header-top').height();
      } else if (jQuery('#layout').hasClass('page-header-menu-fixed')) {
        pos = pos - jQuery('.page-header-menu').height();
      }
      pos = pos + (offeset ? offeset : -1 * el.height());
    }

    jQuery('html,body').animate({
        scrollTop: pos
    }, 'slow');
  }

  /* Initialize the sclimScroll */
  _initSlimScroll(el) {
    jQuery(el).each(() => {
      if (jQuery(el).attr("data-initialized")) {
        return; // exit
      }

      let height;

      if (jQuery(el).attr("data-height")) {
        height = jQuery(el).attr("data-height");
      } else {
        height = jQuery(el).css('height');
      }

      jQuery(el).slimScroll({
        allowPageScroll: true, // allow page scroll when the element scroll is ended
        size: '7px',
        color: (jQuery(el).attr("data-handle-color") ? jQuery(el).attr("data-handle-color") : '#bbb'),
        wrapperClass: (jQuery(el).attr("data-wrapper-class") ? jQuery(el).attr("data-wrapper-class") : 'slimScrollDiv'),
        railColor: (jQuery(el).attr("data-rail-color") ? jQuery(el).attr("data-rail-color") : '#eaeaea'),
        position: this.isRTL ? 'left' : 'right',
        height: height,
        alwaysVisible: (jQuery(el).attr("data-always-visible") == "1" ? true : false),
        railVisible: (jQuery(el).attr("data-rail-visible") == "1" ? true : false),
        disableFadeOut: true
      });

      jQuery(el).attr("data-initialized", "1");
    });
  }

  /* Scroll to the top */
  _scrollTop() {
      this._scrollTo();
  }

  /* Get the corrent view port */
  _getViewPort() {
    let e = window, a = 'inner';
    if (!('innerWidth' in window)) {
      a = 'client';
      e = document.documentElement || document.body;
    }

    return {
      width: e[a + 'Width'],
      height: e[a + 'Height']
    };
  }

  /* Destroy slimScroll */
  _destroySlimScroll(el) {
    jQuery(el).each((index, element) => {
      if (jQuery(element).attr("data-initialized") === "1") { // destroy existing instance before updating the height
        jQuery(element).removeAttr("data-initialized");
        jQuery(element).removeAttr("style");

        let attrList = {};

        // store the custom attribures so later we will reassign.
        if (jQuery(element).attr("data-handle-color")) {
          attrList["data-handle-color"] = jQuery(element).attr("data-handle-color");
        }
        if (jQuery(element).attr("data-wrapper-class")) {
          attrList["data-wrapper-class"] = jQuery(element).attr("data-wrapper-class");
        }
        if (jQuery(element).attr("data-rail-color")) {
          attrList["data-rail-color"] = jQuery(element).attr("data-rail-color");
        }
        if (jQuery(element).attr("data-always-visible")) {
          attrList["data-always-visible"] = jQuery(element).attr("data-always-visible");
        }
        if (jQuery(element).attr("data-rail-visible")) {
          attrList["data-rail-visible"] = jQuery(element).attr("data-rail-visible");
        }

        jQuery(element).slimScroll({
          wrapperClass: (jQuery(element).attr("data-wrapper-class") ? jQuery(element).attr("data-wrapper-class") : 'slimScrollDiv'),
          destroy: true
        });

        let the = jQuery(element);

        // reassign custom attributes
        jQuery.each(attrList, (key, value) => {
          the.attr(key, value);
        });
      }
    });
  }

  /* Handle group element heights */
  _handleHeight() {
    jQuery('[data-auto-height]').each((index, element) => {
      let parent = jQuery(element);
      let items = jQuery('[data-height]', parent);
      let height = 0;
      let mode = parent.attr('data-mode');
      let offset = parseInt(parent.attr('data-offset') ? parent.attr('data-offset') : 0);

      items.each(() => {
        if (jQuery(element).attr('data-height') == "height") {
            jQuery(element).css('height', '');
        } else {
            jQuery(element).css('min-height', '');
        }

        let height_ = (mode == 'base-height' ? jQuery(element).outerHeight() : jQuery(element).outerHeight(true));
        if (height_ > height) {
          height = height_;
        }
      });

      height = height + offset;

      items.each(() => {
        if (jQuery(element).attr('data-height') == "height") {
          jQuery(element).css('height', height);
        } else {
          jQuery(element).css('min-height', height);
        }
      });

      if(parent.attr('data-related')) {
        jQuery(parent.attr('data-related')).css('height', parent.height());
      }
     });
  }

  /* Handle the layout reinitialization on window resize */
  _handleOnResize() {
    let resize;
    if (this.isIE8) {
      let currheight;
      jQuery(window).resize(() => {
        if (currheight == document.documentElement.clientHeight) {
          return; //quite event since only body resized not window.
        }
        if (resize) {
          clearTimeout(resize);
        }
        resize = setTimeout(() => {
          this._runResizeHandlers();
        }, 50); // wait 50ms until window resize finishes.
        currheight = document.documentElement.clientHeight; // store last body client height
      });
    } else {
      jQuery(window).resize(() => {
        if (resize) {
          clearTimeout(resize);
        }
        resize = setTimeout(() => {
          this._runResizeHandlers();
        }, 50); // wait 50ms until window resize finishes.
      });
    }
  }

  /* Runs callback functions set by this.addResponsiveHandler() */
  _runResizeHandlers() {
    // reinitialize other subscribed elements
    for (let i = 0; i < this.resizeHandlers.length; i++) {
      let each = this.resizeHandlers[i];
      each.call(this);
    }
  }

  /* Initializes main settings */
  _handleInit() {
    if (jQuery('#layout').css('direction') === 'rtl') {
      this.isRTL = true;
    }

    this.isIE8 = !!navigator.userAgent.match(/MSIE 8.0/);
    this.isIE9 = !!navigator.userAgent.match(/MSIE 9.0/);
    this.isIE10 = !!navigator.userAgent.match(/MSIE 10.0/);

    if (this.isIE10) {
      jQuery('html').addClass('ie10'); // detect IE10 version
    }

    if (this.isIE10 || this.isIE9 || this.isIE8) {
      jQuery('html').addClass('ie'); // detect IE10 version
    }
  }
}
