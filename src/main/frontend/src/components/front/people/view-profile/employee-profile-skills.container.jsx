import React from "react";
import * as employeeActions from '../../../../services/actions/employee.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import EmployeeProfileSkills from './employee-profile-skills.component.jsx';
import SkillTransformer from '../../../../services/transformers/skill.transformer';
import toastr from 'toastr';

class EmployeeProfileSkillsContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showSkill: false
    }
  }

  componentDidMount() {
    this.listEmployeeSkills(this.props.employeeId);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.employeeId != nextProps.employeeId) {
      this.listEmployeeSkills(nextProps.employeeId);
    } else {
      this.setState({showSkill: true});
    }
  }

  render() {
    if (this.state.showSkill && this.props.skills.length > 0) {
      return (
        <EmployeeProfileSkills skillCategories={SkillTransformer.groupSkillsToCategories(this.props.skills)} />
      )
    } else {
      return null;
    }
  }

  listEmployeeSkills(id) {
    this.props.actions.listEmployeeSkills(id)
      .fail(error => {
        toastr.error(`There is a problem with fetching of employee skills`);
        this.context.router.push('/people');
      });
  }
}

EmployeeProfileSkillsContainer.propTypes = {
  employeeId: React.PropTypes.number.isRequired,
  skills: React.PropTypes.array.isRequired,
  actions: React.PropTypes.object.isRequired
};

EmployeeProfileSkillsContainer.contextTypes = {
  router: React.PropTypes.object
};

function mapStateToProps(state, ownProps) {
  return {
    skills: state.employeeSkills
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(employeeActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeProfileSkillsContainer);
