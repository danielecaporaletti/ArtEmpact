import React from "react";
import { GoArrowLeft } from "react-icons/go";

export default function CreativeProfileDescription({ count, setCount }) {
  return (
    <div className="w-[23.315rem] h-[23.0625rem] mt-[.94rem] bg-grey-blue rounded-[1.875rem] relative flex">
      <div className="arrowColumn w-[3.69rem]">
        <div
          className="w-7 h-7 bg-grey-arrow rounded-full absolute top-[11.56rem] translate-x-1/2"
          onClick={() => setCount(count - 1)}
        >
          <GoArrowLeft
            size={"65%"}
            style={{
              color: "#0f53b8 ",
              position: "absolute",
              top: "0.3rem",
              left: "0.3rem",
            }}
          />
        </div>
      </div>
      <div className="w-[15.75rem] h-[13.625rem] mt-[2.88rem] mr-[3.62rem]">
        <h3 className="text-[1rem] text-white font-bold">Descrizione</h3>
        <p className="text-[.875rem] text-white font-light">
          Descrizione Lorem ipsum dolor sit amet, consectetur adipiscing elit.
          Sed dapibus tortor dui, sed consectetur sapien congue imperdiet.
          Pellentesque arcu massa, aliquam eget sem nec, imperdiet euismod
          lacus. Maecenas venenatis leo vel mattis ultricies. Quisque non ex
          erat. Donec eu ipsum vulputate, condimentum augue ac, tempus velit.
          Fusce dignissim erat in tellus tristique, tincidunt tristique est
          placerat.
        </p>
      </div>
    </div>
  );
}
