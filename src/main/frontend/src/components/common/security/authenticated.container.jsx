import React from 'react';
import {connect} from 'react-redux';
import SecurityService from '../../../services/services/security-service';

class Authenticated extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isAuthorized: SecurityService.hasRequiredRoles(this.props.authenticatedUser, this.props.requiredRoles)
    }
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.authenticatedUser !== nextProps.authenticatedUser) {
      this.setState({isAuthorized: SecurityService.hasRequiredRoles(nextProps.authenticatedUser, this.props.requiredRoles)});
    }
  }

  render() {
    if (this.state.isAuthorized) {
      return (
        <div style={{display: 'inline-block'}}>
          {this.props.children}
        </div>
      );
    } else {
      return null;
    }
  }
}

Authenticated.propTypes = {
  authenticatedUser: React.PropTypes.object,
  requiredRoles: React.PropTypes.array
};

function mapStateToProps(state, ownProps) {
  return {
    authenticatedUser: state.authenticatedUser
  };
}

export default connect(mapStateToProps)(Authenticated);
