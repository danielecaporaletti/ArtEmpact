import React from "react";
import logo from "../icons/LogoAE_simple.png";
import IconBell from "../icons/IconBell";
import IconOption from "../icons/IconOption";

const VerticalHeader = ({ onClick }) => {
  return (
    <div className="flex flex-col items-center gap-4">
      <div className="w-[50px] h-[50px] rounded-full bg-white shadow-md p-4">
        <IconOption />
      </div>
      <div
        onClick={onClick}
        className="w-[50px] h-[50px] rounded-full bg-white shadow-md p-4"
      >
        <IconBell color="blue" />
      </div>
      <div>
        <img src={logo} alt="" />
      </div>
    </div>
  );
};

export default VerticalHeader;
