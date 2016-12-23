export default class Skillsransformer {

  /**
   * Trasnsform list from skills and map them to group from skills categories
   */
  static groupSkillsToCategories(skills) {
    let skillCategories = [];
    let categories = [];
    let i = 0;
    skills.map(skill => {
      if (skillCategories[skill.category.title] == undefined) {
        skillCategories[skill.category.title] = i++;
        categories[skillCategories[skill.category.title]] = {
            title:  skill.category.title,
            skills: []
        }
      }

      categories[skillCategories[skill.category.title]].skills.push(skill);
    });

    return categories;
  }
}
