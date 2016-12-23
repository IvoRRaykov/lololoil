import React from "react";
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import WelcomeBody from './welcome-body.component.jsx';
import * as moduleMenuActions from '../../../../services/actions/module-menu.actions';

/**
 * Welcome page container represents the inital page where the user can select to which module to be redirected
 */
class WelcomePageContainer extends React.Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    setTimeout(() => this.props.actions.openModuleMenu(), 500);
  }

  render() {
    return (
        <WelcomeBody/>
    )
  }
}

WelcomePageContainer.propTypes = {
  actions: React.PropTypes.object.isRequired
};

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(moduleMenuActions, dispatch)
  };
}

export default connect((state, ownProps) => {return {};}, mapDispatchToProps)(WelcomePageContainer);
