import React from "react";
import logo from "../../assetTemporanei/logo_iscrizione.png";
import arrowBack from "../../assetTemporanei/arrowBack.png";
import { useNavigate } from "react-router-dom";

const StepForm = ({ step, page, setPage }) => {
  const navigate = useNavigate();
  const backStep = () => {
    if (page === 0) {
      navigate(-1);
    }
    setPage(page - 1);
  };
  return (
    <>
      <div className="flex flex-col justify-between">
        <div className="pt-4 pb-4 sm:mt-0 w-full z-10 flex flex-col relative items-center justify-center">
          <div onClick={backStep} className="absolute top-8 left-8">
            <img src={arrowBack} alt="" />
          </div>
          <div className="w-[136px]">
            <img src={logo} alt="logo" />
          </div>
          <div className="flex mt-4 gap-2">
            {step.map((el, i) => {
              return (
                <span
                  key={i}
                  className={`${
                    i === page ? "bg-white w-8" : "w-3"
                  } h-3 rounded-full border border-white`}
                ></span>
              );
            })}
          </div>
        </div>
        {step[page]}
      </div>
    </>
  );
};

export default StepForm;
