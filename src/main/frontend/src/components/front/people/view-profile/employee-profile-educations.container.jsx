import React from "react";
import * as employeeActions from '../../../../services/actions/employee.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import EmployeeProfileEducations from './employee-profile-educations.component.jsx';
import toastr from 'toastr';

class EmployeeProfileEducationsContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showEducations: false
    }
  }

  componentDidMount() {
    this.listEmployeeEducations(this.props.employeeId);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.employeeId != nextProps.employeeId) {
      this.listEmployeeEducations(nextProps.employeeId);
    } else {
      this.setState({showEducations: true});
    }
  }

  render() {
    if (this.state.showEducations && this.props.educations.length > 0) {
      return (
        <EmployeeProfileEducations educations={this.props.educations} />
      )
    } else {
      return null;
    }
  }

  listEmployeeEducations(id) {
    this.props.actions.listEmployeeEducations(id)
      .fail(error => {
        toastr.error(`There is a problem with fetching of employee educations`);
        this.context.router.push('/people');
      });
  }
}

EmployeeProfileEducationsContainer.propTypes = {
  employeeId: React.PropTypes.number.isRequired,
  educations: React.PropTypes.array.isRequired,
  actions: React.PropTypes.object.isRequired
};

EmployeeProfileEducationsContainer.contextTypes = {
  router: React.PropTypes.object
};

function mapStateToProps(state, ownProps) {
  return {
    educations: state.employeeEducations
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(employeeActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeProfileEducationsContainer);
