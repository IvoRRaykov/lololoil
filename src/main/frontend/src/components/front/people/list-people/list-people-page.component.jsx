import React from "react";
import PageTitle from "../../../common/layout/page-title/page-title.component.jsx";
import Breadcrumb from "../../../common/layout/breadcrumb/breadcrumb.component.jsx";
import EmployeeListView from "./employee-list-view.component.jsx";

const ListPeoplePage = ({breadcrumbNodes, employees}) => {
  return (
    <section className="container-fluid">
      <PageTitle mainTitle="People" subTitle="list all people" />
      <article className="page-bar">
        <Breadcrumb nodes={breadcrumbNodes} />
      </article>

      <article id="list-people-content" className="row">
        <EmployeeListView employees={employees}/>
      </article>
    </section>
  )
};

ListPeoplePage.propTypes = {
  employees: React.PropTypes.arrayOf(React.PropTypes.object),
  breadcrumbNodes: React.PropTypes.arrayOf(React.PropTypes.object)
};

export default ListPeoplePage;
