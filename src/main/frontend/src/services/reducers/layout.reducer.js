import * as ActionTypes from '../actions/action-types.constants';
import InitialStates from './initial-states.reducer';

export default function layoutContentReducer(state = InitialStates.hideContent, action) {
  switch(action.type) {
    case ActionTypes.LAYOUT_CONTENT_VISABILITY:
      return action.hideContent;
    default:
      return state;
  }
}
