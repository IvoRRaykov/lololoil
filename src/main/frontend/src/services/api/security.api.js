import jQuery from "jquery";
import Configuration from "../configuration";

export default class SecurityAPI {

  /**
   * List all Employees only with their main data
   */
  static signIn(credentials) {
    let headers = {"Authorization": "Basic " + btoa(`${credentials.login}:${credentials.password}`)};

    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}user`,
      headers: headers,
      contentType: "application/json"
    });
  }

  /**
   * Logout the authenticated user
   */
  static logout() {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}logout`,
      contentType: "application/json"
    });
  }

  /**
   * Get Authenticated user
   */
  static getAuthenticatedUser() {
    return jQuery.ajax({
      type: "GET",
      url: `${Configuration.SERVER_PATH}user`,
      contentType: "application/json"
    });
  }
}
