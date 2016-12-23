import * as ActionTypes from './action-types.constants';
import {beginAjaxCall, ajaxCallSuccess, ajaxCallError} from './ajax-status.actions.js';
import EmployeeAPI from '../api/employee.api.js';

/* ------------------------------ Public API ------------------------------- */

/**
 * List all active employee loaded only with their main data
 */
export function listActiveEmployeesWithMainData() {
  return function(dispatch, getState, a, b, c, d) {
    dispatch(beginAjaxCall());
    return EmployeeAPI.listWithMainData().then(employees => {
      dispatch(ajaxCallSuccess());
      dispatch(_loadEmployeesSuccess(employees));
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  };
}

/**
 * List all skills of employee
 */
export function listEmployeeSkills(id) {
  return function(dispatch) {
    dispatch(beginAjaxCall());
    return EmployeeAPI.listEmployeeSkills(id).then(skills => {
      dispatch(ajaxCallSuccess());
      dispatch(_loadEmployeeSkillsSuccess(skills));
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  };
}

/**
 * List all interests of employee
 */
export function listEmployeeInterests(id) {
  return function(dispatch) {
    dispatch(beginAjaxCall());
    return EmployeeAPI.listEmployeeInterests(id).then(interests => {
      dispatch(ajaxCallSuccess());
      dispatch(_loadEmployeeInterestsSuccess(interests));
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  };
}

/**
 * List all languages of employee
 */
export function listEmployeeLanguages(id) {
  return function(dispatch) {
    dispatch(beginAjaxCall());
    return EmployeeAPI.listEmployeeLanguages(id).then(languages => {
      dispatch(ajaxCallSuccess());
      dispatch(_loadEmployeeLanguagesSuccess(languages));
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  };
}

/**
 * List all certifications of employee
 */
export function listEmployeeCertifications(id) {
  return function(dispatch) {
    dispatch(beginAjaxCall());
    return EmployeeAPI.listEmployeeCertifications(id).then(certifications => {
      dispatch(ajaxCallSuccess());
      dispatch(_loadEmployeeCertificationsSuccess(certifications));
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  };
}

/**
 * Load specific Employee
 */
export function loadSpecificEmployee(id) {
  return function(dispatch) {
    dispatch(beginAjaxCall());
    return EmployeeAPI.getById(id).then(employee => {
      dispatch(ajaxCallSuccess());
      dispatch(_loadSpecificEmployeeSuccess(employee));
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  }
}


/**
 * List all educations of employee
 */
export function listEmployeeEducations(id) {
  return function(dispatch) {
    dispatch(beginAjaxCall());
    return EmployeeAPI.listEmployeeEducations(id).then(educations => {
      dispatch(ajaxCallSuccess());
      dispatch(_loadEmployeeEducationsSuccess(educations));
    }).fail(error => {
      dispatch(ajaxCallError(error));
    });
  };
}

/* ------------------------------ Dispatchers ------------------------------- */

/**
 * Load the employees successfully
 */
export function _loadEmployeesSuccess(employees) {
  return {type: ActionTypes.LOAD_EMPLOYEES_SUCCESS, employees: employees};
}

/**
 * Load the employee successfully
 */
export function _loadSpecificEmployeeSuccess(employee) {
  return {type: ActionTypes.LOAD_SPECIFIC_EMPLOYEE_SUCCESS, employee: employee};
}

/**
 * Load specific employee skills successfully
 */
export function _loadEmployeeSkillsSuccess(skills) {
  return {type: ActionTypes.LOAD_SPECIFIC_EMPLOYEE_SKILLS_SUCCESS, skills: skills};
}

/**
 * Load specific employee interests successfully
 */
export function _loadEmployeeInterestsSuccess(interests) {
  return {type: ActionTypes.LOAD_SPECIFIC_EMPLOYEE_INTERESTS_SUCCESS, interests: interests};
}

/**
 * Load specific employee languages successfully
 */
export function _loadEmployeeLanguagesSuccess(languages) {
  return {type: ActionTypes.LOAD_SPECIFIC_EMPLOYEE_LANGUAGES_SUCCESS, languages: languages};
}

/**
 * Load specific employee certifications successfully
 */
export function _loadEmployeeCertificationsSuccess(certifications) {
  return {type: ActionTypes.LOAD_SPECIFIC_EMPLOYEE_CERTIFICATIONS_SUCCESS, certifications: certifications};
}

/**
 * Load specific employee certifications successfully
 */
export function _loadEmployeeEducationsSuccess(educations) {
  return {type: ActionTypes.LOAD_SPECIFIC_EMPLOYEE_EDUCATIONS_SUCCESS, educations: educations};
}
