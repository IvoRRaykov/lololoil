import React from "react";
import {connect} from 'react-redux';
import ModuleMenu from './module-menu.component.jsx';
import jQuery from 'jquery';
import Snap from 'imports-loader?this=>window,fix=>module.exports=0!snapsvg/dist/snap.svg.js';
import { browserHistory } from 'react-router';

const CLOSE_MODULE_MENU = 0;

/**
 * Module menu container will serves like a module dispatcher.
 * It shows the main modules of the application and the user can switch to each one
 */
class ModuleMenuContainer extends React.Component {
  constructor(props) {
    super(props);

		this.closeBtnClickEvent = this.closeBtnClickEvent.bind(this);
		this.openBtnClickEvent = this.openBtnClickEvent.bind(this);
    this.moduleClickEvent = this.moduleClickEvent.bind(this);
  }

	componentDidMount() {
		this.initProperties();

    let forbitModalCloseOnOutsiteClick = this.props.forbitModalCloseOnOutsiteClick ? this.props.forbitModalCloseOnOutsiteClick : false;
		let showOpenBtn = this.props.showOpenBtn !== undefined ? this.props.showOpenBtn : true;

		if (showOpenBtn) {
    	jQuery('.page-footer').prepend('<button class="menu-button mx-auto" id="open-button"><img src="/assets/images/layout/power.png" /></button>');
			jQuery('#open-button').on('click', this.openBtnClickEvent);
		}

    if (!forbitModalCloseOnOutsiteClick) {
  		// close the menu element if the target it´s not the menu element or one of its descendants..
  		jQuery(this.content).on('click', (event) => {
  			let target = event.target;
        let isMenuScope = jQuery(target).parents('#module-menu').length == 1;
				let isOpenBtn = jQuery(target).parents('#open-button').length == 1;

  			if( !isOpenBtn && !isMenuScope && this.isOpen && target !== this.openbtn ) {
  				this.closeMenu();
  			}
  		});
    }
	}

	componentWillUnmount() {
		jQuery(this.content).off('click');
	}

	componentWillReceiveProps(nextProps) {
		if (this.props.moduleMenuStatus != nextProps.moduleMenuStatus) {
			if (CLOSE_MODULE_MENU == nextProps.moduleMenuStatus) {
				this.closeMenu();
			} else if (nextProps.moduleMenuStatus > 0) {
	      this.openMenu();
	    }
		}
	}

  render() {
    return (
      <ModuleMenu
				showCloseBtn={this.props.showCloseBtn !== undefined ? this.props.showCloseBtn : true}
				closeBtnClick={this.closeBtnClickEvent}
        moduleClick={this.moduleClickEvent}
				/>
    )
  }

  /**
   * Helper function for initializing the container properties
   */
	initProperties() {
    this.bodyEl = jQuery('#module-menu');
    jQuery(document.body).addClass('content-wrap');
		this.content = document.querySelector('.content-wrap');
		this.openbtn = document.getElementById('open-button');
		this.closebtn = document.getElementById('close-button');
		this.isOpen = false;

		this.morphEl = document.getElementById( 'morph-shape' );
		this.s = Snap( this.morphEl.querySelector( 'svg' ) );
		this.path = this.s.select( 'path' );
		this.initialPath = this.path.attr('d');
		this.pathOpen = this.morphEl.getAttribute( 'data-morph-open' );
		this.isAnimating = false;
  }

	/**
	 * Dispatch the user to desired module, when the user click to it
	 */
	moduleClickEvent(url) {
		browserHistory.push(url);
	}

	/**
	 * Close the module menu, when the X btn is clicked
	 */
	closeBtnClickEvent() {
		this.toggleModuleMenu();
	}

	/**
	 * Open the module menu, when the Open menu button is clicked
	 */
	openBtnClickEvent() {
		this.toggleModuleMenu();
	}

	/**
	 * Toggle the menu display condition - open/close
	 */
	toggleModuleMenu() {
		if(this.isAnimating) {
			return false;
		}

		this.isAnimating = true;
		if( this.isOpen ) {
			jQuery(this.bodyEl).removeClass('show-menu');
			// animate path
			setTimeout( () => {
				// reset path
				this.path.attr( 'd', this.initialPath );
				this.isAnimating = false;
			}, 300 );
		} else {
			jQuery(this.bodyEl).addClass('show-menu');
			// animate path
			this.path.animate({ 'path' : this.pathOpen }, 400, mina.easeinout, () => this.isAnimating = false);
		}

		this.isOpen = !this.isOpen;
	}

	/**
	 * Open the module menu - when is used by action module-menu.actions.js#openModuleMenu()
	 */
	openMenu() {
		if(this.isAnimating) {
			return false;
		}
		this.isAnimating = true;
		jQuery(this.bodyEl).addClass('show-menu');
		// animate path
		this.path.animate({ 'path' : this.pathOpen }, 400, mina.easeinout, () => this.isAnimating = false);
		this.isOpen = true;
	}

	/**
	 * Close the module menu - when is used by action module-menu.actions.js#closeModuleMenu()
	 */
	closeMenu() {
		if(this.isAnimating) {
			return false;
		}
		this.isAnimating = true;
		jQuery(this.bodyEl).removeClass('show-menu');
		// animate path
		setTimeout( () => {
			// reset path
			this.path.attr( 'd', this.initialPath );
			this.isAnimating = false;
		}, 300 );
		this.isOpen = false;
	}
}

ModuleMenuContainer.propTypes = {
  moduleMenuStatus: React.PropTypes.number.isRequired,
  dispatch: React.PropTypes.func.isRequired
};

function mapStateToProps(state, ownProps) {
  return {
    moduleMenuStatus: state.moduleMenuStatus
  };
}

export default connect(mapStateToProps)(ModuleMenuContainer);
