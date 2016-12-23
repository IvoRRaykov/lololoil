import React from 'react';
import TextInput from '../form/text-input.component.jsx';
import CheckboxInput from '../form/checkbox-input.component.jsx';

const LoginForm = ({credential, onSave, onChange, saving, errors}) => {
  return (
    <form className="login-form animated bounceInUp offset-md-2 col-md-8 offset-lg-4 col-lg-4 ">
      <figure className="logo mx-auto">
        <img src="/assets/images/proxiad-logo.png" className="mx-auto"/>
      </figure>

      <TextInput
        type="text"
        labelClass="cols-xs-12 col-sm-3 col-lg-2 col-form-label" label="Login"
        inputWrapperClass="cols-xs-12 col-sm-9 col-lg-10"
        name="login"
        value={credential.login}
        onChange={onChange} />

      <TextInput
        type="password"
        labelClass="cols-xs-12 col-sm-3 col-lg-2 col-form-label" label="Password"
        inputWrapperClass="cols-xs-12 col-sm-9 col-lg-10"
        name="password"
        value={credential.password}
        onChange={onChange} />

      {/**<CheckboxInput value="Automatically sign me next time" />**/}

      <input
        type="submit"
        className="btn btn-ocustom mx-auto"
        disabled={saving}
        value={saving ? 'Sign in...' : 'Sign in'}
        onClick={onSave} />
    </form>
  )
}

LoginForm.propTypes = {
  credential: React.PropTypes.object.isRequired,
  onSave: React.PropTypes.func.isRequired,
  onChange: React.PropTypes.func.isRequired,
  saving: React.PropTypes.bool,
  errors: React.PropTypes.object
}

export default LoginForm;
