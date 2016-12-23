import * as ActionTypes from './action-types.constants';

/**
 * Action for beginning of new Ajax call
 */
export function beginAjaxCall() {
  return {type: ActionTypes.BEGIN_AJAX_CALL};
}

/**
 * Action mark the for successful ajax operation
 */
export function ajaxCallSuccess() {
  return {type: ActionTypes.AJAX_CALL_SUCCESS};
}

/**
 * Action for error handling due Ajax error
 */
export function ajaxCallError() {
  return {type: ActionTypes.AJAX_CALL_ERROR};
}
