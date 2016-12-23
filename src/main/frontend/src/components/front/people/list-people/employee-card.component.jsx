import React from "react";
import SocialLinks from '../common/social-links.component.jsx';
import EmployeeAPI from '../../../../services/api/employee.api';
import { Link } from 'react-router';

const EmployeeCard = ({employee}) => {
  return (
  <div className="col-xs-12 col-sm-6 col-md-4 col-lg-3 employee-card-wrapper animated fadeIn flipInY">
    <div className="employee-card  mx-auto">
    <div className="employee-card-image">
      <img src={EmployeeAPI.getProfilePictureUrlByEmployeeId(employee.id)} alt="" />
      <div className="employee-card-detail">
        <SocialLinks details={employee.details} />
        <Link to={`/people/${employee.id}`} className="btn btn-ocustom">View Profile</Link>
      </div>
    </div>
    <div className="employee-card-descr font-alt">
      <div className="employee-card-name">
        {employee.firstName} {employee.lastName}
      </div>
      <div className="employee-card-role">
        {employee.position}
      </div>
    </div>
    </div>
  </div>
  );
};

EmployeeCard.propTypes = {
  employee: React.PropTypes.object.isRequired
};

export default EmployeeCard;
