import React, { useState } from "react";
import IconEyeOpen from "../icons/IconEyeOpen";
import IconEyeClosed from "../icons/IconEyeClosed";

const InputPassword = ({
  value,
  label,
  text,
  visible,
  onChange,
  name,
  required,
}) => {
  const [isVisible, setIsVisible] = useState(true);
  const icon = () => {
    if (isVisible) {
      return <IconEyeClosed />;
    } else {
      return <IconEyeOpen />;
    }
  };
  const handlerVisible = () => {
    setIsVisible(!isVisible);
  };
  return (
    <label htmlFor={name} className="block">
      <span className="block text-sm font-medium text-slate-700">{label}</span>
      <div className="flex items-center group">
        <input
          id={name}
          type={isVisible ? "password" : "text"}
          value={value}
          placeholder={text}
          hidden={visible}
          onChange={onChange}
          name={name}
          required={required}
          className="mt-1 mr-3 block w-full sm:h-[48px] h-[56px] px-3 py-2 bg-white border border-[#99B8DD] rounded-xl text-sm shadow-sm placeholder-slate-400
      focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500
      disabled:bg-slate-50 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none
      invalid:text-red-600 invalid:focus:border-red-600
      "
        />
        <button onClick={handlerVisible}>{icon()}</button>
      </div>
    </label>
  );
};

export default InputPassword;
