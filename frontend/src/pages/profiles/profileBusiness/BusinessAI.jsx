import React from "react";
import LogoSimpBig from "../../../icons/LogoSimp_big";
import IconWand from "../../../icons/IconWand";
import FooterNavBar from "../../../components/FooterNavBar";
const activePage = "home";

export default function BusinessAI() {
  return (
    <>
      <div className="min-w-[24.375rem] min-h-screen bg-white">
        <div className="max-w-[24.375rem] m-auto bg-white pb-[7rem] flex flex-col items-center">
          {/* Logo */}
          <div className="w-[8.1875rem] h-[7.4375rem] mt-[9.75rem] object-cover">
            <LogoSimpBig />
          </div>
          {/* Testo centrale */}
          <div className="w-[18.1875rem] text-center mt-8">
            <h3 className="text-[1.25rem] font-montserrat font-bold">
              Ciao, sono l’AI di ArtEmpact
            </h3>
            <p className="text-[1rem] font-semibold mt-4">
              💡 Scopri ogni giorno come l’AI può aiutarti a trovare nuove
              collaborazioni!
            </p>
            <p className="text-[1rem] mt-[2rem]">
              Fatti aiutare dall’intelligenza artificiale!
            </p>
          </div>
          {/* btn Inizia */}
          <div className="primary-btn px-[5.62rem] py-[.94rem] mt-[12rem]">
            <div className="w-[1.2rem] aspect-square">
              <IconWand />
            </div>
            <p className="text-[1rem] text-white pl-[.3rem]">Inizia</p>
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
