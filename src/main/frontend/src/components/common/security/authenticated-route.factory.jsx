import React from 'react';
import * as securityActions from '../../../services/actions/security.actions';
import * as layoutActions from '../../../services/actions/layout.actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

export default function(ComposedComponent, requiredRoles, redirectURL) {
  /**
   * Authentication control over the pages
   */
  class AuthenticatedRoute extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        showComponent: this.props.showComponent
      }
    }

    componentDidMount() {
      this.props.actions.hideLayout(true);
      this.props.actions.authenticatePage(this.props.location.pathname, requiredRoles, redirectURL);
    }

    componentWillReceiveProps(nextProps) {
      if (nextProps.showComponent == true) {
        this.props.actions.hideLayout(false);
      }

      if (this.props.showComponent != nextProps.showComponent) {
        this.setState({showComponent: nextProps.showComponent});
      }
    }

    render() {
      if (this.state.showComponent) {
        return <ComposedComponent {...this.props} />;
      } else {
        return null;
      }
    }
  }

  function mapStateToProps(state, ownProps) {
    return {
      showComponent: state.showComponent
    };
  }

  function mapDispatchToProps(dispatch) {
    return {
      actions: bindActionCreators(Object.assign({}, securityActions, layoutActions), dispatch)
    };
  }

  return connect(mapStateToProps, mapDispatchToProps)(AuthenticatedRoute);
}
