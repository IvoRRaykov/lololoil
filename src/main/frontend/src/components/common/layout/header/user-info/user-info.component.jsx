import React from "react";
import EmployeeAPI from '../../../../../services/api/employee.api';
import { Link } from 'react-router';
import Authenticated from '../../../security/authenticated.container.jsx';
import NotAuthenticated from '../../../security/not-authenticated.container.jsx';

const UserInfo = ({authenticatedUser, onLogout}) => {
  return (
    <li className="nav-item dropdown dropdown-user">
      <a href="javascript:;" className="nav-link dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
        <Authenticated>
          {authenticatedUser  && <img className="rounded-circle" src={EmployeeAPI.getProfilePictureUrlByEmployeeId(authenticatedUser.userId)}/>}
          {authenticatedUser  && <span className="username username-hide-on-mobile"> {authenticatedUser.firstName}</span>}
        </Authenticated>
        <NotAuthenticated>
          <img className="rounded-circle" src="/assets/images/layout/avatar.png"/>
        </NotAuthenticated>
      </a>
      <ul className="dropdown-menu dropdown-menu-default">
        <li className="dropdown-item">
          <NotAuthenticated>
            <Link to={`/login`}>
              <i className="icon-user"></i> Login
            </Link>
          </NotAuthenticated>
          <Authenticated>
            {authenticatedUser &&
              <Link to={`/people/${authenticatedUser.userId}`}>
                <i className="icon-user"></i> My Profile
              </Link>
            }
          </Authenticated>
        </li>
        <li className="divider"> </li>
        <li className="dropdown-item">
          <Authenticated>
            <a onClick={onLogout}>
              <i className="icon-key"></i> Log Out
            </a>
          </Authenticated>
        </li>
      </ul>
    </li>
  );
}

UserInfo.propTypes = {
  authenticatedUser: React.PropTypes.object,
  onLogout: React.PropTypes.func.isRequired
}

export default UserInfo;
