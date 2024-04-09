import React from "react";
import IconBack from "../../../icons/IconBack";
import FooterNavBar from "../../../components/FooterNavBar";
import ProfileHeader_Settings from "../../../components/ProfileHeader_Settings";
const activePage = "profilo";

export default function BusinessSettings() {
  return (
    <>
      {/* Container Card */}
      <div className="w-full min-w-[24.375rem] min-h-screen bg-white">
        <div className="max-w-[24.375rem] m-auto bg-white pt-[1.06rem] px-[.69rem]">
          {/* Header */}
          <ProfileHeader_Settings />
          <div className=" flex items-center gap-[.44rem] font-normal mt-6">
            {/* Indietro */}
            <IconBack />
            <a
              href="#"
              className="text-[.8125rem] text-primary-color cursor-pointer"
            >
              indietro
            </a>
          </div>
          {/* Section title */}
          <div className="mt-5">
            <h3 className="text-[1.25rem] font-monserrat font-semibold">
              Impostazioni Account
            </h3>
          </div>
          {/* Modalità di pagamento */}
          <div className="mt-[.94rem]">
            <h4 className="h4">Gestisci le modalità di pagamento</h4>
            <div className="text-[.875rem] flex flex-col gap-[.31rem] mt-[.63rem]">
              <input
                type="text"
                id="titolare"
                name="titolare"
                placeholder="Nome del Titolare"
                className="input__field-settings"
              />
              <input
                type="text"
                id="numero_carta"
                name="numero_carta"
                placeholder="Numero carta"
                className="input__field-settings"
              />
              <div className="flex gap-[5.75rem]">
                {/* Type da sistemare con js?????????????????????????????????????????? */}
                <input
                  type="number"
                  id="scadenza"
                  name="scadenza"
                  placeholder="MM/YY"
                  className="input__field-settings w-[6.5rem]"
                />
                <input
                  type="number"
                  maxLength="3"
                  id="cvv"
                  name="cvv"
                  placeholder="CVV"
                  className="input__field-settings w-[6.5rem]"
                />
              </div>
              <input
                type="text"
                id="email"
                name="email"
                placeholder="Email"
                className="input__field-settings"
              />
            </div>
          </div>
          <div className="w-[9.25rem] m-auto mt-[1.56rem]">
            <button className="primary-btn w-full text-[1rem] text-white font-montserrat font-medium px-[1.25rem] py-[.25rem] rounded-[.5625rem]">
              Salva carta
            </button>
          </div>
          {/* Recapiti */}
          <div className="mt-[2.19rem] flex flex-col gap-[.62rem]">
            <h4 className="h4">Email</h4>
            <input
              type="email"
              id="email"
              name="email"
              placeholder="Inserisci la tua email"
              className="input__field-settings"
            />
            <h4 className="h4">Numero di Telefono</h4>
            <input
              type="number"
              id="tel"
              name="tel"
              placeholder="Inserisci il tuo numero di telefono"
              className="input__field-settings"
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
