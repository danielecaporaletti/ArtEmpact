import React from "react";
import homeBusiness from "../assetTemporanei/home-business.jpg";
import businessText from "../assetTemporanei/azienda_text.png";

const BusinessLink = () => {
  return (
    <div className="relative">
      
    <div className="w-[178px] h-[229px] md:w-[284px] md:h-[365px] rightClip mt-12">
      <img
        className="w-full h-full brightness-50"
        src={homeBusiness}
        alt="link to creative form"
      />
    </div>
    <img className="absolute top-0 left-2 drop-shadow-md" src={businessText} alt="Sono un azienda"/>
    </div>
  );
};

export default BusinessLink;
