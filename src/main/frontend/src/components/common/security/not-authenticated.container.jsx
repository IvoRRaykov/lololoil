import React from 'react';
import {connect} from 'react-redux';

class NotAuthenticated extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isAuthenticated: this.props.authenticatedUser != null
    }
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.authenticatedUser !== nextProps.authenticatedUser) {
      this.setState({isAuthenticated: nextProps.authenticatedUser != null});
    }
  }

  render() {
    if (this.state.isAuthenticated) {
      return null;
    } else {
      return (
        <div style={{display: 'inline-block'}}>
          {this.props.children}
        </div>
      );
    }
  }
}

NotAuthenticated.propTypes = {
  authenticatedUser: React.PropTypes.object,
};

function mapStateToProps(state, ownProps) {
  return {
    authenticatedUser: state.authenticatedUser
  };
}

export default connect(mapStateToProps)(NotAuthenticated);
