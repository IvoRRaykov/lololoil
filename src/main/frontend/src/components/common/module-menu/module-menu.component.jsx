import React from "react";
import ModuleMenuCard from './module-menu-card.component.jsx';


const ModuleMenu = ({showCloseBtn, closeBtnClick, moduleClick}) => {
  return (
    <div id="module-menu">
      <div className="menu-wrap animated fadeIn">
        <nav className="menu">
          <div className="module-list">
            <div className="container-fluid">
              <div className="row">
                <ModuleMenuCard
                  imgSrc="/assets/images/news.png"
                  title="News"
                  url=""
                  moduleClick={moduleClick} />
                <ModuleMenuCard
                  imgSrc="/assets/images/people.png"
                  title="People"
                  url="/people"
                  moduleClick={moduleClick} />
                <ModuleMenuCard
                  imgSrc="/assets/images/hiring.jpg"
                  title="Hiring"
                  url=""
                  moduleClick={moduleClick}/>
                <ModuleMenuCard
                  imgSrc="/assets/images/days-off.png"
                  title="Days off"
                  url=""
                  moduleClick={moduleClick}/>
              </div>
            </div>
          </div>
        </nav>
        {showCloseBtn && <button className="close-button" id="close-button" onClick={closeBtnClick}>Close Menu</button>}
        <div className="morph-shape" id="morph-shape" data-morph-open="M0,100h1000V0c0,0-136.938,0-224,0C583,0,610.924,0,498,0C387,0,395,0,249,0C118,0,0,0,0,0V100z">
          <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 1000 100" preserveAspectRatio="none">
            <path d="M0,100h1000l0,0c0,0-136.938,0-224,0c-193,0-170.235-1.256-278-35C399,34,395,0,249,0C118,0,0,100,0,100L0,100z"/>
          </svg>
        </div>
      </div>
    </div>
  );
};

ModuleMenu.propTypes = {
  showCloseBtn: React.PropTypes.bool.isRequired,
  closeBtnClick: React.PropTypes.func.isRequired,
  moduleClick: React.PropTypes.func.isRequired
};

export default ModuleMenu;
