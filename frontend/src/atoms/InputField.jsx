import React from "react";

const InputField = ({
  type,
  value,
  label,
  text,
  visible,
  onChange,
  name,
  required,
  size,
}) => {
  return (
    <label htmlFor={name} className="block">
      <span className="block text-sm font-medium text-slate-700">{label}</span>

      <input
        id={name}
        type={type}
        value={value}
        placeholder={text}
        hidden={visible}
        onChange={onChange}
        name={name}
        required={required}
        className={`${
          size === "small"
            ? "w-full border-b-2 text-sm bg-transparent  px-2 py-3"
            : "mt-1 block w-full sm:h-[48px] h-[56px] px-3 py-2 bg-white border border-[#99B8DD] rounded-xl text-sm shadow-sm placeholder-slate-400 focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500 disabled:bg-slate-50 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none invalid:text-red-600 invalid:focus:border-red-600"
        } ${name === "nome" ? "text-xl" : "text-sm"}`}
      />
    </label>
  );
};

export default InputField;
