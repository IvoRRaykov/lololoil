import React from 'react';
import EmployeeAPI from '../../../../services/api/employee.api';
import SocialLinks from '../common/social-links.component.jsx';
import EmployeeDetailsInfo from './employee-details-info.component.jsx';

const EmployeeProfileViewIntro = ({employee}) => {
  return (
    <div className="employee-profile-intro animated fadeIn">
      <div className="bg-wrapper">
        <div className="row">
          <div className="content-wrapper col-md-12 col-lg-10 offset-lg-1">
            <div className="col-xs-12 col-md-7 col-lg-6">
              <div className="hidden-md-up text-xs-center">
                <img className="small-profile-pic" src={EmployeeAPI.getProfilePictureUrlByEmployeeId(employee.id)}/>
                <SocialLinks details={employee.details}/>
              </div>
              <div className="name text-xs-center">{employee.firstName} {employee.lastName}</div>
              <h1 className="job-position text-xs-center">{employee.position}</h1>
              <EmployeeDetailsInfo employee={employee} />
            </div>
            <div className="hidden-sm-down col-md-5 col-lg-6 text-xs-center">
                <img className="big-profile-pic" src={EmployeeAPI.getProfilePictureUrlByEmployeeId(employee.id)}/>
                <SocialLinks details={employee.details}/>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

EmployeeProfileViewIntro.propTypes = {
  employee: React.PropTypes.object.isRequired
};

export default EmployeeProfileViewIntro;
