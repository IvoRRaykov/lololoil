import React from "react";

export default class MenuElementSection extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <li className="nav-item start ">
        {this.props.children}
      </li>
    );
  }
}
