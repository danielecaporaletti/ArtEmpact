import React from "react";
import ImagePreview from "../atoms/ImagePreview";
import Button from "../atoms/Button";
import FooterNavBar from "./FooterNavBar";
import IconBell from "../icons/IconBell";
import IconCircle from "../icons/IconCircle";
import arrowBack from "../assetTemporanei/arrowBack.png";
import { Link } from "react-router-dom";

const ArtistPreview = ({ id, nome, img, settore, toggle, backPage }) => {
  return (
    <div className="w-full max-w-md">
      <div className="relative flex justify-between items-center mt-8 mb-8">
        <div className="w-[30px] h-[30px] rounded-full bg-white shadow-md p-1">
          <IconCircle color="blue" />
        </div>
        <div
          onClick={toggle}
          className="w-[30px] h-[30px] rounded-full bg-white shadow-md p-2"
        >
          <IconBell color="blue" />
        </div>
      </div>
      <div
        onClick={backPage}
        className="flex items-center gap-2 mb-[25px] cursor-pointer"
      >
        <img src={arrowBack} alt="vai al profilo" />
      </div>
      <ImagePreview image={img}>
        <h3 className="font-montserrat text-black text-[19px]">{settore}</h3>
        <p className="font-jost text-[#2F2F2F] text-[12px]  ">{nome}</p>
      </ImagePreview>
      <div className="flex justify-between gap-2 mt-6 mb-16">
        <Button>
          <span className="text-[16px]">Scrivi un messaggio</span>
        </Button>
        <Button size="small">
          <Link to="/home/creative/profile">
            <span className="text-[16px]">Visita il profilo</span>
          </Link>
        </Button>
      </div>
      <div className="fixed w-full bottom-2 left-1/2 -translate-x-1/2 max-w-md px-4 md:hidden">
        <FooterNavBar />
      </div>
    </div>
  );
};

export default ArtistPreview;
