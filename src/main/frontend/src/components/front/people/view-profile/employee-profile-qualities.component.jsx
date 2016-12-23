import React from "react";
import FontAwesome  from "react-fontawesome";

const EmployeeProfileQualities = () => {
  return (
    <div className="employee-profile-qualities animated fadeIn">
      <div className="row">
        <div className="element col-xs-4 text-xs-center">
         <div className="icon">
           <FontAwesome name="superpowers" />
         </div>
         <div className="text">Unique Skills</div>
       </div>
       <div className="element col-xs-4 text-xs-center">
        <div className="icon">
          <FontAwesome name="diamond" />
        </div>
        <div className="text">Qualities</div>
      </div>
      <div className="element col-xs-4 text-xs-center">
       <div className="icon">
         <FontAwesome name="graduation-cap" />
       </div>
       <div className="text">Education</div>
     </div>
    </div>
  </div>
  );
};

export default EmployeeProfileQualities;
