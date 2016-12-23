import React, {PropTypes} from 'react';

const CheckboxInput = ({onChange, value}) => {
  return (
    <div className="checkbox">
      <label><input type="checkbox" onChange={onChange}/> {value}</label>
    </div>
  )
};

CheckboxInput.propTypes = {
  onChange: React.PropTypes.func,
  value: React.PropTypes.string.isRequired,
}

export default CheckboxInput;
