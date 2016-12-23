import React from 'react';
import PageTitle from "../../../common/layout/page-title/page-title.component.jsx";
import Breadcrumb from "../../../common/layout/breadcrumb/breadcrumb.component.jsx";
import EmployeeProfileViewIntro from './employee-profile-intro.component.jsx';
import EmployeeProfileSkillsContainer from './employee-profile-skills.container.jsx';
import EmployeeProfileInterestsContainer from './employee-profile-interests.container.jsx';
import EmployeeProfileLanguagesContainer from './employee-profile-languages.container.jsx';
import EmployeeProfileCertificationsContainer from './employee-profile-certifications.container.jsx';
import EmployeeProfileEducationsContainer from './employee-profile-educations.container.jsx';
import EmployeeProfileQualities from './employee-profile-qualities.component.jsx';

const EmployeeProfileViewPage = ({employee, breadcrumbNodes}) => {
  return (
    <section className="container-fluid">
      <PageTitle mainTitle="Employee" subTitle="the employee profile" />
      <article className="page-bar">
        <Breadcrumb nodes={breadcrumbNodes} />
      </article>

      <article id="employee-profile-content">
        {employee.id && <EmployeeProfileViewIntro employee={employee} /> }
        <EmployeeProfileQualities />
        {employee.id && <EmployeeProfileSkillsContainer employeeId={employee.id}/> }
        {employee.id && <EmployeeProfileInterestsContainer employeeId={employee.id}/> }
        {employee.id && <EmployeeProfileLanguagesContainer employeeId={employee.id}/> }
        {employee.id && <EmployeeProfileCertificationsContainer employeeId={employee.id}/> }
        {employee.id && <EmployeeProfileEducationsContainer employeeId={employee.id}/> }
      </article>
    </section>
  )
}

EmployeeProfileViewPage.propTypes = {
  employee: React.PropTypes.object.isRequired,
  breadcrumbNodes: React.PropTypes.arrayOf(React.PropTypes.object)
};

export default EmployeeProfileViewPage;
