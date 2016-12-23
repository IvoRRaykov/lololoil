import * as ActionTypes from '../actions/action-types.constants';
import InitialStates from './initial-states.reducer';

export function employeeListReducer(state = InitialStates.employees, action) {
  switch(action.type) {
    case ActionTypes.LOAD_EMPLOYEES_SUCCESS:
      return action.employees;
    default:
      return state;
  }
}

export function employeeSkillsReducer(state = InitialStates.skills, action) {
  switch(action.type) {
    case ActionTypes.LOAD_SPECIFIC_EMPLOYEE_SKILLS_SUCCESS:
      return action.skills;
    default:
      return state;
  }
}

export function employeeInterestsReducer(state = InitialStates.interests, action) {
  switch(action.type) {
    case ActionTypes.LOAD_SPECIFIC_EMPLOYEE_INTERESTS_SUCCESS:
      return action.interests;
    default:
      return state;
  }
}

export function employeeReducer(state = InitialStates.employee, action) {
  switch(action.type) {
    case ActionTypes.LOAD_SPECIFIC_EMPLOYEE_SUCCESS:
      return action.employee;
    default:
      return state;
  }
}

export function employeeLanguagesReducer(state = InitialStates.languages, action) {
  switch(action.type) {
    case ActionTypes.LOAD_SPECIFIC_EMPLOYEE_LANGUAGES_SUCCESS:
      return action.languages;
    default:
      return state;
  }
}

export function employeeCertificationsReducer(state = InitialStates.certifications, action) {
  switch(action.type) {
    case ActionTypes.LOAD_SPECIFIC_EMPLOYEE_CERTIFICATIONS_SUCCESS:
      return action.certifications;
    default:
      return state;
  }
}

export function employeeEducationReducer(state = InitialStates.educations, action) {
  switch(action.type) {
    case ActionTypes.LOAD_SPECIFIC_EMPLOYEE_EDUCATIONS_SUCCESS:
      return action.educations;
    default:
      return state;
  }
}
