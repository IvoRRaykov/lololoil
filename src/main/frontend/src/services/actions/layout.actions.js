import * as ActionTypes from './action-types.constants';

/* ------------------------------ Public API ------------------------------- */

/**
 * Hide or show the layout content
 */
export function hideLayout(hideContent) {
  return function(dispatch) {
    dispatch(_setHideContent(false));
  }
}

export function _setHideContent(hideContent) {
  return {type: ActionTypes.LAYOUT_CONTENT_VISABILITY, hideContent : hideContent};
}
