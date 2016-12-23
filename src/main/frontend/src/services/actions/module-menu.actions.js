import * as ActionTypes from './action-types.constants';

const CLOSE_MODULE_MENU = 0;

/**
 * Open the module menu
 */
export function openModuleMenu() {
  return {type: ActionTypes.OPEN_MODAL_MENU};
}

/**
 * Close the module menu
 */
export function closeModuleMenu() {
  return {type: ActionTypes.CLOSE_MODAL_MENU, moduleMenuStatus: CLOSE_MODULE_MENU};
}
