import React from "react";
import FooterNavBar from "../../../components/FooterNavBar";
import IconBack from "../../../icons/IconBack";
import LogoAE from "../../../icons/LogoAE";
import LogoAEsimp from "../../../icons/LogoAE_simple";
const activePage = "home";

export default function BusinessAI_2() {
  return (
    <>
      <div className="min-w-[24.375rem] min-h-screen bg-white">
        <div className="max-w-[24.375rem] h-full m-auto bg-primary-color  flex flex-col items-center">
          <div className="w-[21.625rem] h-[51.125rem] bg-white rounded-[1.875rem] mt-[1.06rem] mx-[1.38rem]">
            <div className="mt-5 ml-5">
              <IconBack />
            </div>
            <div className="flex flex-col gap-[.56rem]">
              <div className="flex gap-[.38rem] mt-[1.69rem] ml-5">
                <div className="w-[2.5rem] h-[2.25rem]">
                  <LogoAEsimp />
                </div>
                <div className="">
                  <span className="text-[1.25rem]font-montserrat font-bold">
                    ArtEmpact
                  </span>
                </div>
              </div>
              <div className="chat_bubble">
                <p className="text-[.75rem] text-white font-light">
                  Ciao, sono il tuo assistente virtuale e ti aiuterò a scoprire
                  come i creativi
                  <br />
                  possono apportare vantaggi alla tua azienda.
                </p>
              </div>
              <div className="chat_bubble">
                <p className="text-[.75rem] text-white font-light">
                  Che tipo di azienda sei?
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
    </>
  );
}
