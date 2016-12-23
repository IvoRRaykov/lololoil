import * as ActionTypes from '../actions/action-types.constants';
import InitialStates from './initial-states.reducer';

export default function moduleMenuReducer(state = InitialStates.moduleMenuStatus, action) {
  switch(action.type) {
    case ActionTypes.OPEN_MODAL_MENU:
    case ActionTypes.CLOSE_MODAL_MENU:
      return new Date().getUTCMilliseconds();
    default:
      return state;
  }
}
