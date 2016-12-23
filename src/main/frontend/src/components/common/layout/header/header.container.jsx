import React from "react";
import Header from './header.component.jsx';
import { browserHistory } from 'react-router';
import * as securityActions from '../../../../services/actions/security.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

class HeaderContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      authenticatedUser: this.props.authenticatedUser
    };
    this.onLogout = this.onLogout.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.authenticatedUser !== nextProps.authenticatedUser) {
      this.setState({authenticatedUser: nextProps.authenticatedUser});
    }
  }

  render() {
    return (
      <Header authenticatedUser={this.state.authenticatedUser} onLogout={this.onLogout} />
    )
  }

  /**
   * Logout the authenticated user and redirect it to home page
   */
  onLogout(event) {
    event.stopPropagation();
    this.props.actions.logout();
  }
}

function mapStateToProps(state, ownProps) {
  return {
    authenticatedUser: state.authenticatedUser
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(securityActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(HeaderContainer);
