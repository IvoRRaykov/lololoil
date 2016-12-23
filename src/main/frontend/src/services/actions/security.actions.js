import * as ActionTypes from './action-types.constants';
import {beginAjaxCall, ajaxCallSuccess, ajaxCallError} from './ajax-status.actions.js';
import SecurityAPI from '../api/security.api.js';
import { browserHistory } from 'react-router';
import toastr from 'toastr';
import SecurityService from '../services/security-service';

/* ------------------------------ Public API ------------------------------- */

/**
 * Sign in the user with specific credentials
 */
export function signIn(credentials) {
  return function(dispatch, getState) {
    dispatch(beginAjaxCall());
    return SecurityAPI.signIn(credentials).then(authenticatedUser => {
      dispatch(_authenticationSuccess(authenticatedUser));
      dispatch(_setLastCheckedTime(new Date().getTime()));
      dispatch(ajaxCallSuccess());
      dispatch(_setShowComponent(true));
      browserHistory.push(getState().redirectedURL);
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  }
}

/**
 * Authenticate page serves to secure some specific page from unauthorized acccess based on roles
 */
export function authenticatePage(redirectedURL, requiredRoles, redirectURL) {
  return function(dispatch, getState) {
    dispatch(_setShowComponent(false));
    dispatch(_setRedirectedURL(redirectedURL));
    if (SecurityService.shouldMadeNewRequest(getState().authenticatedUser != null, getState().lastCheckedTime)) {
      return SecurityAPI.getAuthenticatedUser().then(authenticatedUser => {
        dispatch(_authenticationSuccess(authenticatedUser));
        dispatch(_setLastCheckedTime(new Date().getTime()));
        if (requiredRoles && requiredRoles.length > 0) {
          if (SecurityService.hasRequiredRoles(getState().authenticatedUser, requiredRoles)) {
              dispatch(_setShowComponent(true));
          } else {
            toastr.warning("You haven't needed authorities to access this page");
            browserHistory.push(redirectURL === undefined ? '/' : redirectURL);
          }
        } else {
          dispatch(_setShowComponent(true));
        }
      }).fail(error => {
        browserHistory.push('/login');
      });
    } else if (requiredRoles && requiredRoles.length > 0) {
        if (SecurityService.hasRequiredRoles(getState().authenticatedUser, requiredRoles)) {
            dispatch(_setShowComponent(true));
        } else {
          toastr.warning("You haven't needed authorities to access this page");
          browserHistory.push(redirectURL === undefined ? '/' : redirectURL);
        }
        return;
    } else {
        dispatch(_setShowComponent(true));
        return;
    }
  }
}

/**
 * Logout the authenticated user
 */
 export function logout() {
  return function(dispatch, getState) {
    let states = getState();
    dispatch(beginAjaxCall());
    return SecurityAPI.logout().then(response => {
      dispatch(ajaxCallSuccess());
      dispatch(_authenticationSuccess(null));
      dispatch(_setLastCheckedTime(0));
      browserHistory.push('/');
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  }
}

/**
 * Helper method for refreshing the authentication and keep the user's session live
 */
export function refreshAuthentication() {
  return function (dispatch, getState) {
    return SecurityAPI.getAuthenticatedUser().then(authenticatedUser => {
      dispatch(_authenticationSuccess(authenticatedUser));
      dispatch(_setLastCheckedTime(new Date().getTime()));
    });
  }
}

/* ------------------------------ Dispatchers ------------------------------- */

/**
 * Set show component
 **/
export function _setShowComponent(showComponent) {
  return {type: ActionTypes.SHOW_COMPONENT, showComponent : showComponent};
}

/**
 * Set redirected url after successfully sign in
 */
export function _setRedirectedURL(redirectedURL) {
  return {type: ActionTypes.REDIRECTED_URL, redirectedURL : redirectedURL === undefined ? null : redirectedURL};
}

/**
 * Set into the store the last  time when some request for authentication has been done into the server
 */
export function _setLastCheckedTime(lastCheckedTime) {
  return {type: ActionTypes.LAST_CHECKED_TIME, lastCheckedTime : lastCheckedTime};
}

/**
 *  Marks the authentication as successfully
 */
export function _authenticationSuccess(authenticatedUser) {
  return {type: ActionTypes.LOGIN_SUCCESS, authenticatedUser: authenticatedUser};
}
