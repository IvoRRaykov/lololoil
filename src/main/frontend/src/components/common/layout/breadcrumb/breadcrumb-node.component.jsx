import React from "react";
import { Link } from "react-router";

const BreadcrumbNode = ({ icon, label, url, active }) => {
  if (url) {
    return (
      <li className={`breadcrumb-item ${active ? "active" : ""}`}>
        <i className={icon} />
        <Link to={url} className="nav-btn">
          {label}
        </Link>
      </li>
    );
  }

  return (
    <li className={`breadcrumb-item ${active ? "active" : ""}`}>
      <i className={icon} />
      <span>{label}</span>
    </li>
  );
};

BreadcrumbNode.propTypes = {
  icon: React.PropTypes.string,
  label: React.PropTypes.string,
  url: React.PropTypes.string,
  active: React.PropTypes.bool
};

export default BreadcrumbNode;
