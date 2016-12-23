import React from "react";
import BreadcrumbNode from "./breadcrumb-node.component.jsx";

const Breadcrumb = ({nodes}) => {

  return (
    <ol className="breadcrumb page-breadcrumb">
      <BreadcrumbNode icon={"icon-home"} label="Home" url='/' />
      {nodes.map((node) => <BreadcrumbNode key={node.label} icon={node.icon} label={node.label} url={node.url} active={node.active} />)}
    </ol>
    );
};

Breadcrumb.propTypes = {
  nodes: React.PropTypes.arrayOf(React.PropTypes.object)
};

export default Breadcrumb;
