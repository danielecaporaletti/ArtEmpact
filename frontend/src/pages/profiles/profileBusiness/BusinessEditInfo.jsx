import React from "react";
import ProfileHeader_Settings from "../../../components/ProfileHeader_Settings";
import IconBack from "../../../icons/IconBack";
import BusinessProfilePicture from "../../../icons/BusinessProfilePicture";
import FooterNavBar from "../../../components/FooterNavBar";
const activePage = "profilo";

export default function BusinessEditInfo() {
  return (
    <>
      {/* Container card */}
      <div className="w-full min-w-[24.375rem] min-h-screen bg-white">
        <div className="max-w-[24.375rem] m-auto bg-white pb-[8rem] pt-[1.06rem] px-[.69rem]">
          {/* Header */}
          <ProfileHeader_Settings />
          {/* Indietro */}
          <div className=" flex items-center gap-[.44rem] font-normal mt-6">
            <IconBack />
            <a
              href="#"
              className="text-[.8125rem] text-primary-color cursor-pointer"
            >
              indietro
            </a>
          </div>
          {/* Le tue informazioni */}
          <div className="pl-2 mt-5 leading-[1.2rem]">
            <p className="text-[1.25rem] font-monserrat font-semibold">
              Le tue informazioni
            </p>
            <p className="text-[.5625rem]">
              Modifica i dettagli pubblici del tuo profilo
            </p>
          </div>
          <div className="pl-2 mt-[.56rem]">
            <p className="text-[.875rem] font-montserrat font-semibold">
              Immagine del profilo
            </p>
          </div>
          {/* Container foto */}
          <div className="w-[11.125rem] aspect-square">
            <BusinessProfilePicture />
          </div>
          <p className="text-[.625rem] text-black font-light underline ml-[1.19rem] -translate-y-2">
            Cambia immagine di profilo
          </p>
          {/* Descrizione */}
          <div className="mt-5 pl-2">
            <h3 className="text-[1rem] font-monserrat font-semibold">
              Descrizione
            </h3>
            <div className="w-[19.75rem] h-[4.9375rem] mt-5 pl-2 shrink-0 border-b border-black shadow-textBox-shadow"></div>
          </div>
          {/* Informazioni & input fields */}
          <div className="mt-[2.19rem] flex flex-col gap-5">
            <h3 className="text-[1rem] font-montserrat font-semibold">
              👤 Le tue informazioni
            </h3>
            <input
              type="text"
              id="name"
              name="name"
              placeholder="Nome e Cognome"
              className="input__field"
            />
            <input
              type="text"
              id="birthday"
              name="birthday"
              placeholder="Data di Nascita"
              className="input__field"
            />
            <input
              type="text"
              id="azienda"
              name="azienda"
              placeholder="Nome dell'azienda"
              className="input__field"
            />
            <select
              name="tipo_azienda"
              id="tipo_azienda"
              className="input__field"
            >
              <option value="null">Tipo azienda</option>
              <option value="azienda1">Azienda1</option>
              <option value="azienda2">Azienda2</option>
            </select>
            <input
              type="text"
              id="anno"
              name="anno di fondazione"
              placeholder="Anno di fondazione"
              className="input__field"
            />
            <input
              type="text"
              id="settore"
              name="settore"
              placeholder="Settore"
              className="input__field"
            />
            <input
              type="text"
              id="città"
              name="città"
              placeholder="Città"
              className="input__field"
            />
            <select
              id="politica"
              name="politica di lavoro"
              className="input__field"
            >
              <option value="null">Scegli tra le opzioni</option>
              <option value="valore1">valore1</option>
              <option value="valore2">valore2</option>
              <option value="valore3">valore3</option>
            </select>
            {/* Footer */}
            <div className="fixed bottom-[.56rem] left-[50%] translate-x-[-50%]">
              <FooterNavBar activePage={activePage} />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
