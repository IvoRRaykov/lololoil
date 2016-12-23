import React from "react";

const EmployeeProfileCategorySkill = ({categorySkill}) => {
  return (
    <div className="category-skill row">
      <div className="category-name col-xs-12 text-xs-left">{categorySkill.title}</div>

      {categorySkill.skills.map(skill => (
        <div key={`skill${skill.id}`} className="col-xs-12 col-sm-6 col-md-3 skill">
          <div className="skill-title">{skill.title}</div>
        </div>
      ))}
    </div>
  );
};

EmployeeProfileCategorySkill.propTypes = {
  categorySkill: React.PropTypes.object.isRequired
};

export default EmployeeProfileCategorySkill;
