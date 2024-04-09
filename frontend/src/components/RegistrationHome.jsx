import React from "react";
import { Link } from "react-router-dom";
import logo from "../assetTemporanei/logo_iscrizione.png";
import CreativeLink from "../atoms/CreativeLink";
import BusinessLink from "../atoms/BusinessLink";
import IconAccesibilty from "../icons/IconAccesibilty";
import IconInfo from "../icons/IconInfo";

const RegistrationHome = () => {
  return (
    <>
      <div className="flex flex-col items-center px-4 w-full lg:w-[50%] relative">
        <div className="w-[136px] md:w-[272px] mt-14 md:mt-8 mb-4">
          <img src={logo} alt="logo" />
        </div>
        <div className="text-[28px] font-bold font-montserrat text-[#2F2F2F] text-center mb-10 leading-8">
          <p className="md:visible max-sm:hidden">
            Un nuovo modo di fare impresa
          </p>
          <p className="sm:visible md:hidden">
            Un nuovo modo di <br /> fare impresa
          </p>
        </div>
        <div className="flex justify-between w-full h-[277px] md:h-[400px]">
          <Link to="/registration/creative">
            <CreativeLink />
          </Link>

          <Link to="/registration/business">
            <BusinessLink />
          </Link>
        </div>
        <div>
          <p className="text-[14px] font-jost font-bold mt-4 p-8 md:mt-0 md:pt-0 text-center ">
            Scegli come iscriverti ad ArtEmpact
          </p>
          <p className="text-[13px] font-jost text-center px-4 mb-8">
            Sei un creativo: mostra i tuoi lavori, amplia il tuo network e
            mettiti in contatto con Aziende che cercano un artista. Sei
            un'azienda: presenta la tua vision e mission e trova il creativo più
            adatto alle tue esigenze per avere una nuova leva competitiva.
          </p>
        </div>
        <div className="fixed bottom-8 right-8 flex flex-col gap-4 max-sm:bg-white max-sm:px-6 max-sm:py-3 max-sm:flex-row max-sm:w-[90%] max-sm:right-0 max-sm:rounded-3xl max-sm:justify-between max-sm:bottom-0 max-sm:mx-[5%] max-sm:items-center max-sm:shadow-3xl">
          <IconAccesibilty />
          <IconInfo />
        </div>
      </div>
    </>
  );
};

export default RegistrationHome;
