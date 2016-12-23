import React from "react";
import EmployeeProfileCategorySkill from './employee-profile-category-skill.component.jsx';

const EmployeeProfileSkills = ({skillCategories}) => {
  return (
    <div className="employee-profile-skills animated fadeIn">
      <div className="row">
        <div className="element col-xs-12 text-xs-left">
          <div className="title">Skills</div>
        </div>
        <div className="element col-xs-12 text-xs-center">
          {skillCategories.map(category => <EmployeeProfileCategorySkill key={category.title} categorySkill={category} />)}
        </div>
      </div>
    </div>
  );
};

EmployeeProfileSkills.propTypes = {
  skillCategories: React.PropTypes.arrayOf(React.PropTypes.object)
};

export default EmployeeProfileSkills;
