export default class BirthdateTransformer {
  /**
   * Transform users birthdate to years
   */
  static transformToYears(date) {
    return new Date().getFullYear() - new Date(date).getFullYear();
  }

  /**
   * Format specific date to locale string
   */
  static formatDate(date) {
    return new Date(date).toLocaleDateString();
  }
}
