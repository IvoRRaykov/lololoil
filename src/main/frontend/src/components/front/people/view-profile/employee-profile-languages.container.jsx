import React from "react";
import * as employeeActions from '../../../../services/actions/employee.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import EmployeeProfileLanguages from './employee-profile-languages.component.jsx';
import LanguageTransformer from '../../../../services/transformers/language.transformer';
import toastr from 'toastr';

class EmployeeProfileLanguagesContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showLanguages: false
    }
  }

  componentDidMount() {
    this.listEmployeeLanguages(this.props.employeeId);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.employeeId != nextProps.employeeId) {
      this.listEmployeeLanguages(nextProps.employeeId);
    } else {
      this.setState({showLanguages: true});
    }
  }

  render() {
    if (this.state.showLanguages && this.props.employeeLanguages.length > 0) {
      return (
        <EmployeeProfileLanguages employeeLanguages={LanguageTransformer.transformEmployeeLanguageToLanguageWithLevel(this.props.employeeLanguages)} />
      )
    } else {
      return null;
    }
  }

  listEmployeeLanguages(id) {
    this.props.actions.listEmployeeLanguages(id)
      .fail(error => {
        toastr.error(`There is a problem with fetching of employee languages`);
        this.context.router.push('/people');
      });
  }
}

EmployeeProfileLanguagesContainer.propTypes = {
  employeeId: React.PropTypes.number.isRequired,
  employeeLanguages: React.PropTypes.array.isRequired,
  actions: React.PropTypes.object.isRequired
};

EmployeeProfileLanguagesContainer.contextTypes = {
  router: React.PropTypes.object
};

function mapStateToProps(state, ownProps) {
  return {
    employeeLanguages: state.employeeLanguages
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(employeeActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeProfileLanguagesContainer);
