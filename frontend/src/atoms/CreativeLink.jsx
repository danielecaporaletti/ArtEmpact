import React from "react";
import homeCreativo from "../assetTemporanei/home-creativo.jpg";
import creativeText from "../assetTemporanei/creativo_text.png";

const CreativeLink = () => {
  return (
    <div className="relative">
      <div className="w-[178px] h-[229px] md:w-[284px] md:h-[365px] leftClip">
        <img
          className="w-full h-full brightness-50"
          src={homeCreativo}
          alt="link to creative form"
        />
      </div>
      <img
        className="absolute top-0 drop-shadow-2xl"
        src={creativeText}
        alt="Sono un creativo"
      />
    </div>
  );
};

export default CreativeLink;
