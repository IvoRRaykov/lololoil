import React from "react";
import UserInfo from './user-info/user-info.component.jsx';
import Notifications from './notifications/notifications.component.jsx';

const Header = ({authenticatedUser, onLogout}) => {
  return (
    <div className="page-header navbar navbar-fixed-top">
      <div className="page-header-inner ">
        <div className="page-logo">
          <a href="/"><img src="/assets/images/proxiad-logo.png" alt="logo" className="logo-default" /></a>
          <div className="menu-toggler sidebar-toggler"></div>
        </div>
        <a href="javascript:;" className="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
        <div className="page-top">
          <div className="top-menu">
            <ul className="nav navbar-nav pull-right">
              <Notifications />
              <UserInfo authenticatedUser={authenticatedUser} onLogout={onLogout}/>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

Header.propTypes = {
  authenticatedUser: React.PropTypes.object,
  onLogout: React.PropTypes.func.isRequired
}

export default Header;
