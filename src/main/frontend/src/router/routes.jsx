import React from "react";
import {Route, IndexRoute} from "react-router";
import ProxiadExtranetAppContainer from "../components/proxiad-extranet-app.container.jsx";
import WelcomePageContainer from '../components/front/common/welcome/welcome-page.container.jsx';
import PeopleLayoutContainer from "../components/common/module-layout/people/people-layout.container.jsx";
import ListPeoplePageContainer from "../components/front/people/list-people/list-people-page.container.jsx";
import EmployeeProfileViewPageContainer from "../components/front/people/view-profile/employee-profile-view-page.container.jsx";
import LoginContainer from '../components/common/login/login.container.jsx';
import requireAuth from '../components/common/security/authenticated-route.factory.jsx';

export default (
    <Route path="/" component={ProxiadExtranetAppContainer}>
        <IndexRoute component={WelcomePageContainer}/>
        <Route path="/login" component={LoginContainer} />
        <Route path="/people" component={PeopleLayoutContainer}>
          <IndexRoute component={ListPeoplePageContainer} />
          <Route path=":id" component={EmployeeProfileViewPageContainer} />
        </Route>
    </Route>
);
