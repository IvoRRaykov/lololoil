export default class AuthorityTransformer {
  /**
   * Transform the structure of authorities
   */
  static transformAuthorities(authorities) {
    let newAuthorities = [];

    authorities.map(authority => {
      newAuthorities[authority.authority] = authority.authority;
    });

    return newAuthorities;
  }
}
