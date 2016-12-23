import React from "react";
import * as employeeActions from '../../../../services/actions/employee.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import EmployeeProfileInterests from './employee-profile-interests.component.jsx';
import toastr from 'toastr';

class EmployeeProfileInterestsContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showInterests: false
    }
  }

  componentDidMount() {
    this.listEmployeeInterests(this.props.employeeId);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.employeeId != nextProps.employeeId) {
      this.listEmployeeInterests(nextProps.employeeId);
    } else {
      this.setState({showInterests: true});
    }
  }

  render() {
    if (this.state.showInterests && this.props.interests.length > 0) {
      return (
        <EmployeeProfileInterests interests={this.props.interests} />
      )
    } else {
      return null;
    }
  }

  listEmployeeInterests(id) {
    this.props.actions.listEmployeeInterests(id)
      .fail(error => {
        toastr.error(`There is a problem with fetching of employee skills`);
        this.context.router.push('/people');
      });
  }
}

EmployeeProfileInterestsContainer.propTypes = {
  employeeId: React.PropTypes.number.isRequired,
  interests: React.PropTypes.array.isRequired,
  actions: React.PropTypes.object.isRequired
};

EmployeeProfileInterestsContainer.contextTypes = {
  router: React.PropTypes.object
};

function mapStateToProps(state, ownProps) {
  return {
    interests: state.employeeInterests
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(employeeActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeProfileInterestsContainer);
