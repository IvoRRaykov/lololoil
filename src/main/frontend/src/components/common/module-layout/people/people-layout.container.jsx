import React from 'react';
import LayoutContainer from '../../layout/layout.container.jsx';
import HeaderContainer from '../../layout/header/header.container.jsx';
import Sidebar from '../../layout/sidebar/sidebar.component.jsx';
import MenuElementSection from '../../layout/sidebar/menu-element-section.component.jsx';
import ParentMenuElement from '../../layout/sidebar/parent-menu-element.component.jsx';
import ChildMenuElement from '../../layout/sidebar/child-menu-element.component.jsx';
import SubMenuElementSection from '../../layout/sidebar/sub-menu-element-section.component.jsx';
import Footer from '../../layout/footer/footer.component.jsx';

export default class PeopleLayoutContainer extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <LayoutContainer location={this.props.location}>
        <HeaderContainer />
        <div className="clearfix"> </div>
        <div className="page-container">
          <Sidebar>
            <MenuElementSection>
              <ParentMenuElement iconClassName="icon-people" title="People"/>
              <SubMenuElementSection>
                <ChildMenuElement iconClassName="icon-people" title="List all people" url="/people"/>
              </SubMenuElementSection>
            </MenuElementSection>
          </Sidebar>
          <div className="page-content-wrapper">
            <div className="page-content">
              {this.props.children}
            </div>
          </div>
        </div>
        <Footer />
      </LayoutContainer>
    );
  }
}
