//SlideSection.jsx
import React from "react";
import Yes from "../icons/Yes";
import No from "../icons/No";

function SlideSection({ count, maxPage }) {
  const renderDynamicRectangles = () => {
    return Array.from({ length: maxPage }, (_, index) => (
      <div
        key={index + 1}
        className={`w-full h-[2px] -top-[4rem] bg-white z-5 transition-opacity ${
          index + 1 === count ? "opacity-100" : "opacity-30"
        }`}
      ></div>
    ));
  };

  return (
    <div className="w-full h-[4.75rem] flex justify-between -translate-y-[3.5rem] -translate-x-[.6rem]">
      <div className="h-full aspect-square -top-[4rem] left-0 z-10">
        <No />
      </div>
      <div className="w-full h-full flex items-center p-[5%]">
        {renderDynamicRectangles()}
      </div>
      <div className="h-full aspect-square z-10">
        <Yes />
      </div>
    </div>
  );
}

export default SlideSection;
