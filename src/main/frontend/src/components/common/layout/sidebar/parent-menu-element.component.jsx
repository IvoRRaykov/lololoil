import React from "react";

const ParentMenuElement = ({iconClassName, title}) => {
  return (
    <a href="javascript:;" className="nav-link nav-toggle">
      <i className={iconClassName}></i>
      <span className="title">{title}</span>
      <span className="arrow"></span>
    </a>
  );
};

ParentMenuElement.propTypes = {
  iconClassName: React.PropTypes.string.isRequired,
  title: React.PropTypes.string.isRequired
};

export default ParentMenuElement;
