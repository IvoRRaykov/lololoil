import * as ActionTypes from '../actions/action-types.constants';
import InitialStates from './initial-states.reducer';
import AuthorityTransformer from '../transformers/authority.transformer.js';

export function lastCheckedTimeReducer(state = InitialStates.lastCheckedTime, action) {
  switch(action.type) {
    case ActionTypes.LAST_CHECKED_TIME:
      return action.lastCheckedTime;
    default:
      return state;
  }
}

export function authenticatedUserReducer(state = InitialStates.authenticatedUser, action) {
  switch(action.type) {
    case ActionTypes.LOGIN_SUCCESS:
      let authenticatedUser = action.authenticatedUser;
      if (authenticatedUser != null) {
        authenticatedUser.authorities = AuthorityTransformer.transformAuthorities(authenticatedUser.authorities);
      }
      return authenticatedUser;
    default:
      return state;
  }
}

export function redirectedURLReducer(state = InitialStates.redirectedURL, action) {
  switch(action.type) {
    case ActionTypes.REDIRECTED_URL:
      return action.redirectedURL == null ? InitialStates.redirectedURL : action.redirectedURL;
    default:
      return state;
  }
}

export function showComponentReducer(state = InitialStates.showComponent, action) {
  switch(action.type) {
    case ActionTypes.SHOW_COMPONENT:
      return action.showComponent;
    default:
      return state;
  }
}
