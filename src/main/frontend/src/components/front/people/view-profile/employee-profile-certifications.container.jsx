import React from "react";
import * as employeeActions from '../../../../services/actions/employee.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import EmployeeProfileCertifications from './employee-profile-certifications.component.jsx';
import toastr from 'toastr';

class EmployeeProfileCertificationsContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showCertifications: false
    }
  }

  componentDidMount() {
    this.listEmployeeCertifications(this.props.employeeId);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.employeeId != nextProps.employeeId) {
      this.listEmployeeCertifications(nextProps.employeeId);
    } else {
      this.setState({showCertifications: true});
    }
  }

  render() {
    if (this.state.showCertifications && this.props.certifications.length > 0) {
      return (
        <EmployeeProfileCertifications certifications={this.props.certifications} />
      )
    } else {
      return null;
    }
  }

  listEmployeeCertifications(id) {
    this.props.actions.listEmployeeCertifications(id)
      .fail(error => {
        toastr.error(`There is a problem with fetching of employee certifications`);
        this.context.router.push('/people');
      });
  }
}

EmployeeProfileCertificationsContainer.propTypes = {
  employeeId: React.PropTypes.number.isRequired,
  certifications: React.PropTypes.array.isRequired,
  actions: React.PropTypes.object.isRequired
};

EmployeeProfileCertificationsContainer.contextTypes = {
  router: React.PropTypes.object
};

function mapStateToProps(state, ownProps) {
  return {
    certifications: state.employeeCertifications
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(employeeActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeProfileCertificationsContainer);
