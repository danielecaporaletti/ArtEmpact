import React from "react";

const ImagePreview = ({ image, children }) => {
  return (
    <div className="relative">
      <img
        className="w-full h-auto min-h-[369px] object-cover rounded-[30px] bg-cover shadow-btn-rounded"
        src={image}
        alt="immagine del progetto"
      />
      <div className="absolute w-full h-[74px] bottom-0 pt-9 pb-4 px-6 rounded-b-[30px] bg-white/50 blur-[2px]"></div>
      <div className="absolute bottom-4 left-4">{children}</div>
    </div>
  );
};

export default ImagePreview;
