import React from "react";
import EducationProfile from './education-profile.component.jsx';

const EmployeeProfileEducations = ({educations}) => {
  return (
    <div className="employee-profile-educations animated fadeIn">
      <div className="row">
        <div className="element col-xs-12 text-xs-left">
          <div className="title">Educations</div>
        </div>
        <div className="element col-xs-12 text-xs-center">
          <div className="education-container row">
            {educations.map(education => <EducationProfile key={education.id} education={education} />)}
          </div>
        </div>
      </div>
    </div>
  );
};

EmployeeProfileEducations.propTypes = {
  educations: React.PropTypes.arrayOf(React.PropTypes.object)
}

export default EmployeeProfileEducations;
