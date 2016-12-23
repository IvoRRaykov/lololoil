import React from "react";

const ModuleMenuCard = ({imgSrc, title, url, moduleClick}) => {
  return (
    <div className="col-xs-6 col-md-3">
      <div className="card mx-auto" onClick={() => moduleClick(url)}>
        <img className="card-img-top" src={imgSrc}/>
        <div className="card-block">
          <h5 className="card-title">{title}</h5>
        </div>
      </div>
    </div>
  );
};

ModuleMenuCard.propTypes = {
  imgSrc: React.PropTypes.string.isRequired,
  title: React.PropTypes.string.isRequired,
  url: React.PropTypes.string.isRequired,
  moduleClick: React.PropTypes.func.isRequired,
};

export default ModuleMenuCard;
