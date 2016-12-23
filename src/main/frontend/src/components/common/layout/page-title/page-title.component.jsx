import React from "react";

const PageTitle = ({mainTitle, subTitle}) => {
  return (
    <h3 className="page-title"> {mainTitle} <small>{subTitle}</small></h3>
  );
}

PageTitle.propTypes = {
  mainTitle: React.PropTypes.string.isRequired,
  subTitle: React.PropTypes.string.isRequired
};

export default PageTitle;
