import React from "react";
import WelcomeHeader from './welcome-header.component.jsx';
let TweenLite = require('gsap').TweenLite;
let ease = require('gsap').ease;
import {DynamicHeader} from '../../../common/dynamic-header/dynamic-header';
import jQuery from 'jquery';
import ModuleMenuContainer from '../../../common/module-menu/module-menu.container.jsx';

/**
 * Welcome body component serves to visualize the body of the welcome page
 */
export default class WelcomeBody extends React.Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    DynamicHeader(document.getElementById('large-header'), document.getElementById('background-canvas'));
  }

  render() {
    return (
      <article id="welcomePage" className="content-wrap animated fadeIn">
          <div id="large-header" className="large-header">
            <WelcomeHeader />
  					<canvas id="background-canvas"></canvas>
  					<h1 className="main-title"><span id="group-title" className="animated fadeIn">Proxiad</span> <span id="group-sub-title" className="thin animated flipInX">Group</span></h1>
            <ModuleMenuContainer showCloseBtn={false} showOpenBtn={false} forbitModalCloseOnOutsiteClick={true}/>
  				</div>
      </article>
    )
  }
}
