import React from "react";

const EmployeeProfileInterests = ({interests}) => {
  return (
    <div className="employee-profile-interests animated fadeIn">
      <div className="row">
        <div className="element col-xs-12 text-xs-left">
          <div className="title">Interests</div>
        </div>
        <div className="element col-xs-12 text-xs-center">
          <div className="category-interest row">
            {interests.map(interest => (
              <div key={`interest${interest.id}`} className="col-xs-12 col-sm-6 col-md-3 interest">
                <div className="interest-title">{interest.title}</div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

EmployeeProfileInterests.propTypes = {
  interests: React.PropTypes.arrayOf(React.PropTypes.object)
}

export default EmployeeProfileInterests;
