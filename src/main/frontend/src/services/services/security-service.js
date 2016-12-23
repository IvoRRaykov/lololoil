import Configuration from '../configuration';

export default class SecurityService {
  /**
   * Checks whether the authenticated user - if exists, has all required roles
   */
  static hasRequiredRoles(authenticatedUser, requiredRoles) {
    if (authenticatedUser == null) {
      return false;
    }

    if (authenticatedUser.authorities[Configuration.Roles.SUPER_ADMIN] !== undefined) {
      return true;
    }

    if (requiredRoles !== undefined) {
      for (let i = 0; i < requiredRoles.length; i++) {
        let role = requiredRoles[i];
        if (role.indexOf('ROLE_') == -1) {
          role = 'ROLE_' + role;
        }

        if (authenticatedUser.authorities[role] === undefined) {
          return false;
        }
      }
    }

    return true;
  }

  /**
    * Helper method, which checks whether should be made new real request to the server
    * for that whether the user is still authenticated or its session is expired
    * @returns {boolean}
    */
   static shouldMadeNewRequest(authenticated, lastCheckedTime) {
       let currentTime = new Date().getTime();
       let SECOND = 1000; // 1000 milliseconds is 1 second
       return (!authenticated || (lastCheckedTime == null || Math.round((currentTime - lastCheckedTime)/ SECOND) >= Configuration.TIME_FOR_REQUEST_IN_SECONDS));
   }
}
