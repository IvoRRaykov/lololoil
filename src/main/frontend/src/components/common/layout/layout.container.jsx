import React from "react";
import LayoutComponent from './layout.component.jsx';
import Layout from './layout';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

class LayoutContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hideContent: this.props.hideContent
    };
    this.layout = new Layout();
  }

  componentDidMount() {
    this.layout.init();
    this.layout.setSidebarMenuActiveLink('match', null, this.props.location.pathname);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.hideContent != nextProps.hideContent) {
      this.setState({hideContent: nextProps.hideContent});
    }
  }

  render() {
    if (!this.state.hideContent) {
      return (
        <LayoutComponent>
          {this.props.children}
        </LayoutComponent>
      )
    } else {
      return null;
    }
  }
}

LayoutContainer.propTypes = {
  hideContent: React.PropTypes.bool.isRequired,
};

function mapStateToProps(state, ownProps) {
  return {
    hideContent: state.hideContent
  };
}

export default connect(mapStateToProps)(LayoutContainer);
