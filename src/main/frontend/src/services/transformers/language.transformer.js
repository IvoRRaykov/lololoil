export default class LanguageTransformer {
  static transformEmployeeLanguageToLanguageWithLevel(employeeLanguages) {
    return employeeLanguages.map(employeeLanguage => {
      return {
        level: employeeLanguage.level,
        language: {
           id: employeeLanguage.pk.language.id,
           name: employeeLanguage.pk.language.name,
           flagCss: employeeLanguage.pk.language.flagCss
         }
      }
    })
  }
}
