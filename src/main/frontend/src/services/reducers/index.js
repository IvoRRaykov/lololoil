import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux';
import ajaxStatusReducer from './ajax-status.reducer';
import moduleMenuReducer from './module-menu.reducer';
import * as EmployeeReducers from './employee.reducer';
import * as SecurityReducers from './security.reducer';
import layoutContentReducer from './layout.reducer';

const rootReducer = combineReducers({
  ajaxCallsInProgress: ajaxStatusReducer,
  moduleMenuStatus: moduleMenuReducer,
  routing: routerReducer,
  employees: EmployeeReducers.employeeListReducer,
  employee: EmployeeReducers.employeeReducer,
  employeeSkills: EmployeeReducers.employeeSkillsReducer,
  employeeInterests: EmployeeReducers.employeeInterestsReducer,
  employeeLanguages: EmployeeReducers.employeeLanguagesReducer,
  employeeCertifications: EmployeeReducers.employeeCertificationsReducer,
  employeeEducations: EmployeeReducers.employeeEducationReducer,
  lastCheckedTime: SecurityReducers.lastCheckedTimeReducer,
  authenticatedUser: SecurityReducers.authenticatedUserReducer,
  redirectedURL: SecurityReducers.redirectedURLReducer,
  showComponent: SecurityReducers.showComponentReducer,
  hideContent: layoutContentReducer
});

export default rootReducer;
