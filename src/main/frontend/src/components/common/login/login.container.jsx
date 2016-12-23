import React from 'react';
import Login from './login.component.jsx';
import {DynamicHeader} from '../dynamic-header/dynamic-header';
import jQuery from 'jquery';
import toastr from 'toastr';
import * as securityActions from '../../../services/actions/security.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

class LoginContainer extends React.Component {

  constructor(props) {
      super(props);

      this.state = {
        saving: false,
        errors: {},
        credential: {
          login: '',
          password: ''
        }
      }

      this.updateLoginFormState = this.updateLoginFormState.bind(this);
      this.signIn = this.signIn.bind(this);
  }

  componentDidMount() {
    DynamicHeader(jQuery('body')[0], jQuery('#background-canvas')[0], '163, 173, 197');
  }

  render() {
    return (
      <Login
        credential={this.state.credential}
        saving={this.state.saving} errors={this.state.errors}
        onChange={this.updateLoginFormState} onSave={this.signIn} />
    )
  }

  updateLoginFormState(event) {
    const field = event.target.name;
    let credential = this.state.credential;
    credential[field] = event.target.value;
    this.setState({credential: credential});
  }

  loginFormIsValid() {
    let formIsValid = true;

    if (this.state.credential.login.length < 5) {
      toastr.error('Login must be at least 5 characters.');
      formIsValid = false;
    }

    if (this.state.credential.password.length < 5) {
      toastr.error('Password must be at least 5 characters.');
      formIsValid = false;
    }

    return formIsValid;
  }

  signIn(event) {
    event.preventDefault();

    if (!this.loginFormIsValid()) {
      return;
    }

    this.setState({saving: true});

    this.props.actions.signIn(this.state.credential, '/people')
      .fail(error => {
        toastr.error("There is no user with the same login/password");
        this.setState({saving: false});
      });
  }
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(securityActions, dispatch)
  };
}

export default connect(() => {return {};}, mapDispatchToProps)(LoginContainer);
