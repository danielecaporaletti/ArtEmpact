import React, { useState } from "react";

import NotificationCenter from "../../../components/NotificationCenter";
import FooterNavBar from "../../../components/FooterNavBar";
import BusinessProfilePicture from "../../../icons/BusinessProfilePicture";
import { Link } from "react-router-dom";
import IconEdit from "../../../icons/edit_icon";
import IconVerified from "../../../icons/IconVerified";
import LogoAEsimp from "../../../icons/LogoAE_simple";
import IconMsglBlue from "../../../icons/IconMessageLightBlue";
import IconStarsY from "../../../icons/IconStarsY";
import ProgressBar from "../../../icons/border_advancement";

import ProfileHeaderIconBell from "../../../components/ProfileHeaderIconBell";
const activePage = "profilo";

export default function ProfileBusiness() {
  const [isNotificationCenterOpen, setIsNotificationCenterOpen] =
    useState(false);


  return (
    <>
      {/* Container card */}
      <div className="w-full min-w-[24.375rem] min-h-screen bg-white overflow-x-hidden">
        <div className="max-w-[24.375rem] m-auto bg-white pb-[4rem]">
          {/* Header */}
          <ProfileHeaderIconBell />
          {/* Container Foto */}
          <div className="w-[8rem] mx-auto mt-[1.06rem] relative">
            <BusinessProfilePicture />
            <ProgressBar />
          </div>
          {/* Link completa il tuo prfilo */}
          <div className="">
            <Link
              to="/"
              className="flex text-center text-[.9rem] tracking-tight justify-center mt-[.8rem]"
            >
              <div className="w-4 aspect-square mr-1">
                <IconEdit />
              </div>
              <p>Completa il tuo profilo</p>
            </Link>
          </div>
          {/* Nome e descrizione impresa/creativo*/}
          <div className="w-auto flex flex-col relative">
            <div className="flex flex-row justify-center">
              <div className="h-[1.625rem] text-black font-montserrat font-bold tracking-[.5px] text-[1.3125rem] text-center leading-none mt-4">
                Medi srl
              </div>
              <div className="h-4 aspect-w-1 aspect-h-1 flex translate-y-3 ml-[.44rem]">
                <IconVerified color={`var(--color-primary-color)`} />
              </div>
            </div>
            <div className="mt-[.31rem] font-jost font-normal text-sm tracking-wide leading-normal text-center mb-[1.06rem]">
              Azienda corporate <br /> Settore Edile
            </div>
          </div>
          {/* Tips Container */}
          <div className="w-[22.375rem] h-[3rem] p-[.62rem] text-center bg-primary-color rounded-[2rem] m-auto">
            <p className="text-[.625rem] text-white font-jost font-semibold">
              💡 Scopri ogni giorno come l’AI può aiutarti a trovare nuove
              collaborazioni <br />
              <span className="font-extralight">
                Fatti aiutare dall’intelligenza artificiale!
              </span>
            </p>
          </div>
          {/* Contenitore menu icone */}
          <div className="">
            <div className="w-full h-[8.25rem] flex mt-[2.25rem] snap-x snap-mandatory overflow-x-auto overflow-y-hidden   pl-[.31rem]">
              {/* Riquadro 1: Matches */}
              <div className="min-w-[8.25rem] h-[7.1875rem] p-[.75rem] rounded-[.9375rem] flex flex-col items-center justify-end shadow-btn-rounded mx-[.31rem] snap-start">
                <div className="w-[3.25rem] flex justify-center   ">
                  <LogoAEsimp />
                </div>
                <p className="text-[1rem] font-jost mt-[.62rem]">
                  <span className="text-primary-color font-semibold">1</span>{" "}
                  Match
                </p>
                <p className="text-[.7rem] text-primary-color uppercase mt-[.38rem]">
                  Fai altri Match
                </p>
              </div>
              {/* Riquadro 2: Messages */}
              <div className="min-w-[8.25rem] h-[7.1875rem] p-[.75rem] rounded-[.9375rem] flex flex-col items-center justify-end shadow-btn-rounded mx-[.31rem] snap-center">
                <div className="w-[3.25rem] flex justify-center">
                  <IconMsglBlue />
                </div>
                <p className="text-[1rem] font-jost mt-[.38rem]">
                  <span className="text-light-blue font-semibold">2</span>{" "}
                  Messaggi
                </p>
                <p className="text-[.7rem] text-light-blue uppercase mt-[.38rem]">
                  Scopri i creativi
                </p>
              </div>
              {/* Riquadro 3: Plus */}
              <div className="min-w-[8.25rem] h-[7.1875rem] p-[.75rem] rounded-[.9375rem] flex flex-col items-center justify-center shadow-btn-rounded mx-[.31rem] snap-end">
                <div className="w-[3.25rem] flex justify-center">
                  <IconStarsY />
                </div>
                <p className="text-[.7rem] text-orange uppercase mt-[.38rem]">
                  Passa a plus
                </p>
              </div>
            </div>
            {/* Ricerche */}
            <div className="w-[20rem] pl-2 mt-[1.12rem]">
              <p className="text-[1.25rem] font-monserrat font-semibold">
                Le tue ricerche
              </p>
              <p className="text-[.5625rem]">
                Nella versione Freemium puoi pubblicare 3 richieste di
                collaborazione, acquista l’abbonamento premium per pubblicare
                tutti i progetti che desideri.
              </p>
            </div>
            {/* Cronologia ricerche */}
            <div className="w-full flex gap-[.63rem] mt-[.68rem] ml-[.81rem] pb-[4rem] overflow-x-auto overflow-y-hidden">
              <div className="w-[7.6875rem] aspect-square bg-gradient-research-history-lblue flex justify-center items-center rounded-[.5rem] shrink-0">
                <p className="text-[1rem] font-montserrat font-semibold truncate">
                  Scultore
                </p>
              </div>
              <div className="w-[7.6875rem] aspect-square bg-gradient-research-history-violet flex justify-center items-center rounded-[.5rem] pl-[.44rem] shrink-0">
                <p className="text-[1rem] font-montserrat font-semibold truncate">
                  Social Media Manager
                </p>
              </div>
              <div className="w-[7.6875rem] aspect-square bg-grey-100 flex justify-center items-center rounded-[.5rem] pl-[.44rem] shrink-0">
                <p className="text-[1.8rem] text-primary-color font-montserrat truncate">
                  &#43;
                </p>
              </div>
            </div>
          </div>
          {/* Footer */}
          <div className="fixed bottom-[.56rem] left-[50%] translate-x-[-50%]">
            <FooterNavBar activePage={activePage} />
          </div>
        </div>
      </div>
      {isNotificationCenterOpen && (
        <NotificationCenter
          onClose={() => setIsNotificationCenterOpen(false)}
        />
      )}
    </>
  );
}
