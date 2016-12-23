import React from "react";

export default class SubMenuElemenetSection extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <ul className="sub-menu">
        {this.props.children}
      </ul>
    );
  }
}
