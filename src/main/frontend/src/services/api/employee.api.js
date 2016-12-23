import jQuery from "jquery";
import Configuration from "../configuration";

export default class EmployeeAPI {
  /**
   * List all Employees only with their main data
   */
  static listWithMainData() {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}api/employee?withMainDataOnly=true`,
      contentType: "application/json"
    });
  }

  /**
   * Get profile picture URL based on the employee id
   */
  static getProfilePictureUrlByEmployeeId(id) {
    return `${Configuration.SERVER_PATH}api/employee/${id}/profilePicture`;
  }

  /**
   * Get specific Employee by its id
   */
  static getById(id) {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}api/employee/${id}`,
      contentType: "application/json"
    });
  }

  /**
   * List all employee skills
   */
  static listEmployeeSkills(id) {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}api/employee/${id}/skills`,
      contentType: "application/json"
    });
  }

  /**
   * List all employee interests
   */
  static listEmployeeInterests(id) {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}api/employee/${id}/interests`,
      contentType: "application/json"
    });
  }

  /**
   * List all employee languages
   */
  static listEmployeeLanguages(id) {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}api/employee/${id}/languages`,
      contentType: "application/json"
    });
  }

  /**
   * List all employee languages
   */
  static listEmployeeCertifications(id) {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}api/employee/${id}/certifications`,
      contentType: "application/json"
    });
  }

  /**
   * List all employee educations
   */
  static listEmployeeEducations(id) {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}api/employee/${id}/educations`,
      contentType: "application/json"
    });
  }
}
