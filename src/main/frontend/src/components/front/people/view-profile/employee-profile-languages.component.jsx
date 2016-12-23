import React from "react";

const EmployeeProfileLanguages = ({employeeLanguages}) => {
  return (
    <div className="employee-profile-languages animated fadeIn">
      <div className="row">
        <div className="element col-xs-12 text-xs-left">
          <div className="title">Languages</div>
        </div>
        <div className="element col-xs-12 text-xs-center">
          <div className="language row">
            {employeeLanguages.map(employeeLanguage => (
              <div key={`language${employeeLanguage.language.id}`} className="col-xs-12 col-sm-6 col-md-3 language">
                <div className="language-title">{employeeLanguage.language.name}</div>
                <div className="language-level"><small>{employeeLanguage.level}</small></div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

EmployeeProfileLanguages.propTypes = {
  employeeLanguages: React.PropTypes.arrayOf(React.PropTypes.object)
}

export default EmployeeProfileLanguages;
