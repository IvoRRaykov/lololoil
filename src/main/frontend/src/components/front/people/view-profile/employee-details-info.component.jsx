import React from "react";
import FontAwesome  from "react-fontawesome";
import { OverlayTrigger, Tooltip } from 'react-bootstrap';
import FullWorkplace from '../../../common/workplace/full-workplace.component.jsx';
import BirthdateTransformer from '../../../../services/transformers/birthdate.transformer';

const EmployeeDetailsInfo = ({employee}) => {
  return (
    <div id="employee-details-info" className="description">
        {employee.details.quickDescription && <div>{employee.details.quickDescription}<hr/></div>}
        <div>
          <h4>Profile</h4>
          {employee.details.showBirthdate &&
            <div>
                <strong>Age: </strong>
                {BirthdateTransformer.transformToYears(employee.details.birthdate)}
            </div>
          }
          <div>
              <strong>Address: </strong>
              <FullWorkplace workplace={employee.workplace} />
          </div>
          {employee.details.showPhone && employee.details.phone &&
            <div>
                <strong>Phone: </strong>
                {employee.details.phone}
            </div>
          }
          {employee.details.showPhone && employee.details.phone2 &&
            <div>
                <strong>Phone2: </strong>
                {employee.details.phone2}
            </div>
          }
          {employee.details.fax &&
            <div>
                <strong>Fax: </strong>
                {employee.details.fax}
            </div>
          }
          {employee.details.email &&
            <div>
                <strong>Email: </strong>
                {employee.details.email}
            </div>
          }
          {employee.dateOfJoining &&
            <div>
                <strong>Date of joining: </strong>
                {BirthdateTransformer.formatDate(employee.dateOfJoining)}
            </div>
          }
        </div>
    </div>
  );
};

EmployeeDetailsInfo.propTypes = {
  employee: React.PropTypes.object.isRequired
};

export default EmployeeDetailsInfo;
