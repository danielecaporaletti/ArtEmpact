import React from "react";

const ProjectImages = ({ text, onChange, value, nome }) => {
  return (
    <>
      <label
        htmlFor={nome}
        className="snap-start scroll-mx-6 shrink-0 relative flex flex-col items-center justify-center cursor-pointer w-[123px] h-[121px] bg-[#D9D9D9] rounded-[15px] text-white shadow-md"
      >
        <span className="font-montserrat text-[24px] text-white">+</span>
        <p className="absolute bottom-0 p-2 text-center font-montserrat text-[14px] font-normal text-white">
          Aggiungi una foto
        </p>
      </label>
      <input
        files={value}
        onChange={onChange}
        className="hidden"
        type="file"
        name={nome}
        id={nome}
      />
    </>
  );
};

export default ProjectImages;
