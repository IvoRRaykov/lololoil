import * as ActionTypes from '../actions/action-types.constants';
import InitialStates from './initial-states.reducer';

function actionTypeEndsInSuccess(type) {
  return type.substring(type.length - 8) == '_SUCCESS';
}

export default function ajaxStatusReducer(state = InitialStates.ajaxCallsInProgress, action) {
  if (action.type == ActionTypes.BEGIN_AJAX_CALL) {
    return state + 1;
  } else if (action.type == ActionTypes.AJAX_CALL_ERROR ||
    actionTypeEndsInSuccess(action.type)) {
    return state - 1;
  }
  return state;
}
