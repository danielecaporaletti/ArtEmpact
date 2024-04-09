import React, { useState } from "react";

import LogoAEsimp from "../../../icons/LogoAE_simple";
import IconMsgLBlue from "../../../icons/IconMessageLightBlue";
import IconStarsY from "../../../icons/IconStarsY";
import IconStarsV from "../../../icons/IconStarsV";
import WorkDiscovery from "../../../icons/WorkDiscovery";
import FooterNavBar from "../../../components/FooterNavBar";
import BusinessProfileImage from "./BusinessProfileImage";
import BusinessProfileDescription from "./BusinessProfileDescription";
import ProfileHeaderIconBell from "../../../components/ProfileHeaderIconBell";
const activePage = "home";

export default function BusinessPcreativeV() {
  const [count, setCount] = useState(1);
  return (
    <>
      <div className="w-full min-w-[24.375rem] min-h-screen bg-white">
        <div className="max-w-[24.375rem] m-auto bg-white pt-[1.06rem] px-[.69rem] pb-[7rem]">
          {/* Header */}
          <ProfileHeaderIconBell />
          <div className="arrowColumn ml-[.44rem] mr-[1rem]"></div>
          <div className="pageMain">
            {count === 1 ? (
              <BusinessProfileImage count={count} setCount={setCount} />
            ) : null}
            {count === 2 ? (
              <BusinessProfileDescription count={count} setCount={setCount} />
            ) : null}
          </div>

          <div className="arrowColumn mr-[.44rem]"></div>
          {/* Passa a premium */}
          <div className="w-[23.3125rem] h-[8.5625rem] rounded-[.75rem] relative border border-grey-300 mt-[.88rem]">
            {/* Contenitore menu icone */}
            <div className="w-full h-[8.25rem] flex truncate p-[.31rem] blur pt-[.95rem] pb-[.47rem] my-[.25rem]">
              {/* Riquadro 1: Matches */}
              <div className="min-w-[8.25rem] h-[7.1875rem] p-[.75rem] rounded-[.9375rem] flex flex-col items-center justify-end shadow-btn-rounded mx-[.31rem]">
                <div className="w-[3.25rem] flex justify-center   ">
                  <LogoAEsimp />
                </div>
                <p className="text-[1rem] font-jost mt-[.62rem]">
                  <span className="text-primary-color font-semibold">1</span>{" "}
                  Match
                </p>
                <p className="text-[.7rem] text-black uppercase font-bold mt-[.38rem]">
                  Fai altri Match
                </p>
              </div>
              {/* Riquadro 2: Messages */}
              <div className="min-w-[8.25rem] h-[7.1875rem] p-[.75rem] rounded-[.9375rem] flex flex-col items-center justify-end shadow-btn-rounded mx-[.31rem]">
                <div className="w-[3.25rem] flex justify-center">
                  <IconMsgLBlue />
                </div>
                <p className="text-[1rem] font-jost mt-[.38rem]">
                  <span className="text-light-blue font-semibold">2</span>{" "}
                  Messaggi
                </p>
                <p className="text-[.7rem] text-black uppercase font-bold mt-[.38rem]">
                  Scopri i creativi
                </p>
              </div>
              {/* Riquadro 3: Plus */}
              <div className="min-w-[8.25rem] h-[7.1875rem] p-[.75rem] rounded-[.9375rem] flex flex-col items-center justify-center shadow-btn-rounded mx-[.31rem]">
                <div className="w-[3.25rem] flex justify-center">
                  <IconStarsY />
                </div>
                <p className="text-[.7rem] text-black font-bold uppercase mt-[.38rem]">
                  Passa a plus
                </p>
              </div>
            </div>
            <div className="w-[15.375rem] h-[2.9375rem] text-[1.25rem] absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-3/4">
              <IconStarsV />
            </div>
          </div>
          {/* Work Discovery */}
          <div className="mt-[2.06rem] flex flex-col gap-[.88rem]">
            <div className="pl-2">
              <WorkDiscovery />
            </div>
            {/* Filtri */}
            {/* Città */}
            <div className="text-[.75rem] text-white font-light flex gap-2">
              <p className="filter__item">Milano</p>
              <p className="filter__item">Monza</p>
            </div>
            {/* Tipo Lavoro */}
            <div className="text-[.75rem] text-white font-light flex gap-2">
              <p className="filter__item">Da remoto</p>
              <p className="filter__item">Ibrido</p>
              <p className="filter__item">In sede</p>
            </div>
            {/* Risultati ricerca */}
            <div className="pl-2">
              <div className="w-[8.0625rem] h-[7.1875rem] p-[.625rem] bg-gradient-result-work-discovery-violet rounded-lg flex justify-center items-center">
                <p className="text-[1rem] font-bold">Scultore</p>
              </div>
            </div>
          </div>
          {/* Footer */}
          <div className="fixed bottom-[.56rem] left-[50%] translate-x-[-50%]">
            <FooterNavBar activePage={activePage} />
          </div>
        </div>
      </div>
    </>
  );
}
