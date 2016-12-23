import React from "react";
import BreadcrumbNode from "./BreadcrumbNode.jsx";

const Breadcrumb = ({nodes}) => {

  return (
    <ol className="breadcrumb">
      {nodes.map((node) => <BreadcrumbNode key={node.label} label={node.label} url={node.url} active={node.active} />)}
    </ol>
    );
};

Breadcrumb.propTypes = {
  nodes: React.PropTypes.arrayOf(React.PropTypes.object)
};

export default Breadcrumb;
