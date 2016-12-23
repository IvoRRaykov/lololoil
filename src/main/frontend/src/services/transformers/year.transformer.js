export default class YearTransformer {

  /*
   * Transform miliseconds to years
   */
  static transformMilisecondsToYear(miliseconds) {
    if (miliseconds == null) {
      return null;
    }

    return new Date(miliseconds).getFullYear();
  }
}
