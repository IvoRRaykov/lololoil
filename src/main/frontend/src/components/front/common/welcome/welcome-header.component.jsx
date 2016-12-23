import React from "react";
import {Link} from 'react-router';
import NotAuthenticated from '../../../common/security/not-authenticated.container.jsx';

/**
 * Welcome header component serves to visualize the header of the welcome page
 */
const WelcomeHeader = () => {
  return (
    <header className="container-fluid">
      <div className="row">
      	<div className="col-xs-6">
      		<img src="/assets/images/proxiad-logo.png" className="animated fadeIn"/>
        </div>
        <div className="col-xs-6 text-xs-right">
          <NotAuthenticated>
            <Link to="/login" className="btn btn-sm btn-ocustom login">login</Link>
          </NotAuthenticated>
        	<article className="language animated slideInRight">
            <span className="flag flag-fr"></span>
          	<span className="flag flag-gb"></span>
        	</article>
      </div>
      </div>
    </header>
  );
};

export default WelcomeHeader;
