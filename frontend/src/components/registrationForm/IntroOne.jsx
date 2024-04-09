import React from "react";
import Button from "../../atoms/Button";

const IntroOne = ({
  isTrue,
  page,
  setPage,
  text,
  image,
  handlerClick,
  buttonText,
  intro,
}) => {
  return (
    <div className="h-full w-full md:w-3/4 flex flex-col sm:gap-4 relative items-center justify-start">
      <div
        style={{ backgroundImage: `url(${image})` }}
        className={`${
          isTrue
            ? "bg-white h-[489.35px] justify-end rounded-b-3xl shadow-xl bg-no-repeat bg-bottom"
            : "h-[489.35px] bg-cover bg-center rounded-b-3xl shadow-xl"
        } w-full relative flex flex-col items-center  `}
      >
        {intro && (
          <div className="absolute bottom-16 flex flex-col items-center gap-1 text-[27px] font-montserrat text-white">
            {intro}
          </div>
        )}
      </div>
      <div className="z-10 flex flex-col items-center mb-8">
        <div className="mt-9 ">
          <p className="text-[21px] font-[400] font-jost text-center px-6">
            {text}
          </p>
        </div>
        <div className="flex justify-center mt-8">
          <Button size="small" handleClick={handlerClick}>
            <span className="text-[14px]">{buttonText}</span>
          </Button>
        </div>
      </div>
    </div>
  );
};

export default IntroOne;
