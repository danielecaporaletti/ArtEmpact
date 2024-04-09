import React from "react";
import Logo from "../atoms/Logo";
import IconBell from "../icons/IconBell";
import FooterNavBar from "./FooterNavBar";
import Button from "../atoms/Button";
import ImagePreview from "../atoms/ImagePreview";
import arrowBack from "../assetTemporanei/arrowBack.png";
import { Link } from "react-router-dom";

const ProjectPreview = ({
  image,
  title,
  type,
  year,
  description,
  backToProfile,
  handlerClick,
}) => {
  return (
    <div className="w-full max-w-md">
      <div className="relative flex justify-between items-center mt-8 mb-8">
        <Logo />
        <div
          onClick={handlerClick}
          className="w-[30px] h-[30px] rounded-full bg-white shadow-md p-2"
        >
          <IconBell color="blue" />
        </div>
      </div>
      <div
        onClick={backToProfile}
        className="flex items-center gap-2 mb-[25px] cursor-pointer"
      >
        <img src={arrowBack} alt="vai al profilo" />
        <span className="font-jost text-[13px] text-blue">
          Torna al profilo
        </span>
      </div>
      <ImagePreview image={image}>
        <h3 className="font-montserrat text-black text-[19px]">{title}</h3>
        <p className="font-jost text-[#2F2F2F] text-[12px]  ">
          {type} - {year}
        </p>
      </ImagePreview>
      <div className="w-full max-w-md">
        <h3 className="font-jost text-[16px] my-[25px]">🖊️ Descrizione</h3>
        <p className="font-jost text-[14px]">{description}</p>
      </div>
      <div className="flex justify-between gap-2 mt-6 mb-16">
        <Button size="small">
          <Link to="/">
            <span className="text-[14px]">Vai alla Home</span>
          </Link>
        </Button>

        <Button>
          <span className="text-[14px]">Vai al prossimo progetto</span>
        </Button>
      </div>
      <div className="fixed w-full bottom-2 left-1/2 -translate-x-1/2 max-w-md px-4 md:hidden">
        <FooterNavBar />
      </div>
    </div>
  );
};

export default ProjectPreview;
