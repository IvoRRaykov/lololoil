import React, {PropTypes} from 'react';

const TextInput = ({wrapperClass, labelClass, inputWrapperClass, inputClass, type, name, label, onChange, placeholder, value, error}) => {
  if (wrapperClass === undefined) {
    wrapperClass = 'form-group row';
  }

  if (labelClass === undefined) {
    labelClass = 'col-form-label';
  }

  if (inputWrapperClass === undefined) {
    inputWrapperClass = '';
  }

  if (inputClass === undefined) {
    inputClass = 'form-control btn-ocustom';
  }

  return (
    <div className={wrapperClass}>
      <label className={labelClass} htmlFor={name}>{label}</label>
      <div className={inputWrapperClass}>
        <input
          type="text"
          name={name}
          className={inputClass}
          placeholder={placeholder}
          value={value}
          onChange={onChange}/>
        {error && <div className="alert alert-danger">{error}</div>}
      </div>
    </div>
  );
};

TextInput.propTypes = {
  wrapperClass: PropTypes.string,
  labelClass: PropTypes.string,
  inputWrapperClass: PropTypes.string,
  inputClass: PropTypes.string,
  type: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  placeholder: PropTypes.string,
  value: PropTypes.string,
  error: PropTypes.string
};

export default TextInput;
