import React from "react";
import ProfileHeader_Settings from "../../../components/ProfileHeader_Settings";
import IconBack from "../../../icons/IconBack";
import CreativeProfilePicture from "../../../icons/CreativeProfilePicture";
import FooterNavBar from "../../../components/FooterNavBar";
import CreativeProjectImg1 from "../../../icons/CreativeProject1";
import CreativeProjectImg2 from "../../../icons/CreativeProject2";
const activePage = "profilo";

export default function CreativeEditInfo() {
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
            <CreativeProfilePicture />
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
              id="creativo"
              name="creativo"
              placeholder="Nome del creativo"
              className="input__field"
            />
            <select
              name="tipo_creativo"
              id="tipo_creativo"
              className="input__field"
            >
              <option value="null">Tipo azienda</option>
              <option value="cretivo1">Azienda1</option>
              <option value="creativo2">Azienda2</option>
            </select>
            <input
              type="text"
              id="settore_competenza"
              name="settore di competenza"
              placeholder="Settore di competenza"
              className="input__field"
            />
            <input
              type="text"
              id="città"
              name="città"
              placeholder="Città"
              className="input__field"
            />
            {/* Formazione */}
            <div className="">
              <h3 className="text-[1rem] font-montserrat font-semibold">
                👤 Formazione
              </h3>
              <input
                type="text"
                id="instituto"
                name="instituto"
                placeholder="Nome Instituto"
                className="input__field"
              />
              <input
                type="number"
                id="anno"
                name="anno"
                placeholder="Anno"
                className="input__field"
              />
            </div>
            {/* Work Discovery */}
            <div className="mt-10">
              <h3 className="text-[1.25rem] font-montserrat font-semibold">
                Work Discovery
              </h3>
              <div className="mt-[.94rem] flex flex-col gap-[.62rem]">
                <h4 className="h4">Luogo in cui cerchi il creativo</h4>
                <input
                  type="text"
                  id="città"
                  name="città"
                  placeholder="Inserisci la città"
                  className="input__field-settings"
                />
                <h4 className="h4">Scegli la distanza massima</h4>
                <input
                  type="number"
                  id="distanza"
                  name="distanza"
                  placeholder="Inserisci la distanza in km"
                  className="input__field-settings"
                />
                <h4 className="h4">Preferenza politica di lavoro</h4>
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
              </div>
            </div>
            {/* I tuoi progetti */}
            {/* Da aggiungere "modifica" ed "elimina"************************************************************************************* */}
            <div>
              <h3 className="text-[1.25rem] font-montserrat font-semibold flex flex-col gap-[.88rem]">
                🚀 I Tuoi Progetti
              </h3>
              <div className="flex flex-col gap-[1rem] pr-2 mt-[.87rem]">
                <div className="w-max flex gap-2">
                  <CreativeProjectImg1 />
                  <p className="text-[1.2rem] font-montserrat font-bold">
                    Progetto 1
                  </p>
                </div>
                <div className="w-max flex gap-2">
                  <CreativeProjectImg2 />
                  <p className="text-[1.2rem] font-montserrat font-bold">
                    Progetto 2
                  </p>
                </div>
                <div className="w-[8.0625rem] h-[7.1875rem] bg-grey-100 flex justify-center items-center pl-[.44rem] shrink-0">
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
      </div>
    </>
  );
}
