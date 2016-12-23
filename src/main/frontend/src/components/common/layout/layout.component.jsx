import React from "react";

export default class LayoutComponent extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <article id="layout" className="page-header-fixed page-sidebar-closed-hide-logo page-footer-fixed page-container-bg-solid">
        {this.props.children}
      </article>
    )
  }
}
