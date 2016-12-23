import React from "react";

const EmployeeProfileCertifications = ({certifications}) => {
  return (
    <div className="employee-profile-certifications animated fadeIn">
      <div className="row">
        <div className="element col-xs-12 text-xs-left">
          <div className="title">Certifications</div>
        </div>
        <div className="element col-xs-12 text-xs-center">
          <div className="category-certification row">
            {certifications.map(certification => (
              <div key={`certification${certification.id}`} className="col-xs-12 col-sm-6 col-md-3 certification">
                <div className="certification-title">{certification.name}</div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

EmployeeProfileCertifications.propTypes = {
  certifications: React.PropTypes.arrayOf(React.PropTypes.object)
}

export default EmployeeProfileCertifications;
