import React from "react";
import * as employeeActions from '../../../../services/actions/employee.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import EmployeeProfileViewPage from './employee-profile-view-page.component.jsx';
import toastr from 'toastr';

class EmployeeProfileViewPageContainer extends React.Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    let employeeId = this.props.params['id'];
    this.props.actions.loadSpecificEmployee(employeeId)
      .fail(error => {
        toastr.error(`There is a problem with fetching of specific employee with id: ${employeeId}`);
        this.context.router.push('/people');
      });
  }

  getBreadCrumbNodes() {
    return [ { icon: 'icon-people', label: 'People', url: '/people' }, { label: 'Employee profile page' } ];
  }

  render() {
    if (this.props.employee.id) {
      return (
          <EmployeeProfileViewPage breadcrumbNodes={this.getBreadCrumbNodes()} employee={this.props.employee} />
      )
    } else {
      return null;
    }
  }
}

EmployeeProfileViewPageContainer.propTypes = {
  employee: React.PropTypes.object.isRequired,
  actions: React.PropTypes.object.isRequired
};

EmployeeProfileViewPageContainer.contextTypes = {
  router: React.PropTypes.object
};

function mapStateToProps(state, ownProps) {
  return {
    employee: state.employee
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(employeeActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeProfileViewPageContainer);
