import React from "react";

const Notifications = () => {
  return (
    <li className="nav-item dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
      <a href="javascript:;" className="nav-link dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
        <i className="icon-bell"></i>
        <span className="tag tag-pill tag-info"> 7 </span>
      </a>
      <ul className="dropdown-menu">
        <li className="external">
          <h3><span className="bold">4 pending</span> notifications</h3>
          <a href="#">view all</a>
        </li>
        <li>
          <ul className="dropdown-menu-list scroller" style={{'height': '250px'}} data-handle-color="#637283">
            <li className="dropdown-item">
              <a href="javascript:;">
                <span className="time">just now</span>
                <span className="details">
                  <span className="label label-sm label-icon label-success">
                      <i className="fa fa-plus"></i>
                  </span> New user registered.
                </span>
              </a>
            </li>
            <li className="dropdown-item">
              <a href="javascript:;">
                  <span className="time">3 mins</span>
                  <span className="details">
                    <span className="label label-sm label-icon label-danger">
                        <i className="fa fa-bullhorn"></i>
                    </span> New feedback request
                  </span>
              </a>
            </li>
            <li className="dropdown-item">
              <a href="javascript:;">
                <span className="time">10 mins</span>
                <span className="details">
                  <span className="label label-sm label-icon label-warning">
                    <i className="fa fa-birthday-cake"></i>
                  </span> There have 5 new birthdays next week
                </span>
              </a>
            </li>
          </ul>
        </li>
      </ul>
    </li>
  );
}

export default Notifications;
