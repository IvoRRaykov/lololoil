import React from "react";
import EmployeeCard from "./employee-card.component.jsx";

const EmployeeListView = ({employees}) => {
  return (
  <div className="container">
    <div className="row">
    {employees.map((employee)=> <EmployeeCard key={employee.id} employee={employee}/>)}
    </div>
  </div>
  );
};

EmployeeListView.propTypes = {
  employees: React.PropTypes.arrayOf(React.PropTypes.object)
};

export default EmployeeListView;
