import React from "react";
import YearTransformer from '../../../../services/transformers/year.transformer';

const EducationProfile = ({education}) => {
  return (
    <div className="col-xs-12 col-md-6">
      <div className="education text-xs-left">
        <div className="school text-xs-center">{education.school.name}</div>
        <div className="description text-xs-center">{education.description}</div>
        <div className="years">
          <span className="start-year">{YearTransformer.transformMilisecondsToYear(education.startDate)}</span>
          {education.endDate && <span className="end-year"> - {YearTransformer.transformMilisecondsToYear(education.endDate)}</span>}
        </div>
        <div className="degree">Degree: {education.degree}</div>
        <div className="field-of-study">Field of study: {education.fieldOfStudy}</div>
      </div>
    </div>
  );
};

EducationProfile.propTypes = {
  education: React.PropTypes.object
}

export default EducationProfile;
