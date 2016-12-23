import React from "react";
import * as employeeActions from '../../../../services/actions/employee.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import ListPeoplePage from './list-people-page.component.jsx';
import toastr from 'toastr';

class EmployeeViewPageContainer extends React.Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.actions.listActiveEmployeesWithMainData()
      .fail(error => {
        toastr.error('There is a problem with fetching of the people list');
      });
  }

  getBreadCrumbNodes() {
    return [ { icon: 'icon-people', label: 'People', url: '/people' }, { label: 'List all people'} ];
  }

  render() {
    return (
      <ListPeoplePage breadcrumbNodes={this.getBreadCrumbNodes()} employees={this.props.employees}/>
    )
  }
}

EmployeeViewPageContainer.propTypes = {
  employees: React.PropTypes.array.isRequired,
  actions: React.PropTypes.object.isRequired
};

function mapStateToProps(state, ownProps) {
  return {
    employees: state.employees
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(employeeActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeViewPageContainer);
