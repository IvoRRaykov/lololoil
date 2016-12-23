import React from "react";

export default class ProxiadExtranetAppContainer extends React.Component {
  render() {
    return (
        <article id="proxiad-extranet-application">
          {this.props.children}
        </article>
		);
  }
}
