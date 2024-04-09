import React from "react";

const Button = ({ handleClick, children, type, size, disabled }) => {
  return (
    <button
      type={type}
      onClick={handleClick}
      disabled={disabled}
      className={`${
        size === "small"
          ? "bg-white border-[0.5px] w-full max-w-[200px] hover:border-[0.5px] hover:border-[#623BFF] h-[53px] active:bg-[#623BFF] text-[16px] active:text-white text-[#623BFF] rounded-[14px] px-[15px] py-[15px] shadow-3xl"
          : "bg-[#623BFF] hover:bg-[#7A88F3] px-[5px] py-[15px] h-[53px] w-full  rounded-[14px] text-[16px] text-white"
      }`}
    >
      {children}
    </button>
  );
};

export default Button;
