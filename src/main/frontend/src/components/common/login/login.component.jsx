import React from 'react';
import LoginForm from './login-form.component.jsx';

const Login = ({credential, onSave, onChange, saving, errors}) => {
  return (
    <article id="login" className="animated fadeIn">
      <article id="login-content-wrapper">
        <articlle id="login-content" className="container-fluid">
          <article className="login-form-wrapper col-xs-12">
            <LoginForm
              credential={credential}
              onSave={onSave}
              onChange={onChange}
              saving={saving}
              errors={errors} />
          </article>
        </articlle>
      </article>
      <canvas id="background-canvas"></canvas>
    </article>
  )
}

Login.propTypes = {
  credential: React.PropTypes.object.isRequired,
  onSave: React.PropTypes.func.isRequired,
  onChange: React.PropTypes.func.isRequired,
  saving: React.PropTypes.bool,
  errors: React.PropTypes.object
}

export default Login;
