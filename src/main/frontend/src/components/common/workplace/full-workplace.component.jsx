import React from "react";

const FullWorkplace = ({workplace}) => {
  return (
    <section className="full-workplace">
      <div className="city">City: {workplace.office.city}</div>
      <div className="floor">Floor: {workplace.floor}</div>
      <div className="room">Room: {workplace.room}</div>
    </section>
  );
};

FullWorkplace.propTypes = {
  workplace: React.PropTypes.object.isRequired
};

export default FullWorkplace;
