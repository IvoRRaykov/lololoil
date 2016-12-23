import React from "react";
import { Link } from "react-router";

const ChildMenuElement = ({iconClassName, title, url}) => {
  return (
    <li className="nav-item start">
      <Link to={url} className="nav-link">
        <i className={iconClassName}></i>
        <span className="title">{title}</span>
      </Link>
    </li>
  );
};

ChildMenuElement.propTypes = {
  iconClassName: React.PropTypes.string.isRequired,
  title: React.PropTypes.string.isRequired,
  url: React.PropTypes.string.isRequired,
};

export default ChildMenuElement;
