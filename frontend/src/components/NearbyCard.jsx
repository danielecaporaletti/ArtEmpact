import React from "react";
import arrowBack from "../assetTemporanei/arrowBack.png";
import Logo from "../atoms/Logo";
import CardList from "../atoms/CardList";
import FooterNavBar from "./FooterNavBar";
import IconFilter from "../icons/IconFilter";

const NearbyCard = ({ data }) => {
  return (
    <>
      <div className="w-full max-w-md">
        <div className="flex justify-between items-center mt-8 mb-2">
          <div className="cursor-pointer">
            <img src={arrowBack} alt="vai al profilo" />
          </div>
          <div className="px-12">
            <Logo />
          </div>
        </div>
        <span className="font-montserrat text-[20px] font-bold ">
          Scopri e conosci altri artisti <br /> come te!
        </span>

        <CardList data={data} />
        <div className="flex flex-col items-end">
          <div className="bg-white rounded-full h-9 w-9 flex justify-center items-center p-1">
            <IconFilter />
          </div>
          <span className="text-[12px] w-9 text-center py-1 text-[#623BFF]">
            filtri
          </span>
        </div>

        <div className="fixed w-full bottom-2 left-1/2 -translate-x-1/2 max-w-md px-4 md:hidden">
          <FooterNavBar />
        </div>
      </div>
    </>
  );
};

export default NearbyCard;
