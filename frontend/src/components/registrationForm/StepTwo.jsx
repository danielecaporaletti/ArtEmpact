import React from "react";
import Button from "../../atoms/Button";
import InputFile from "../../atoms/InputFile";

const StepTwo = ({ handlerClick }) => {
  return (
    <div className="relative flex flex-col md:gap-4 items-center justify-between md:w-[390px]">
      <div className="flex flex-col justify-start gap-2 w-full h-[565px] border p-4 pb-8 bg-white rounded-t-3xl z-10">
        <p className="text-[30px] font-bold font-montserrat sm:mt-2 mt-8 mb-6 sm:mb-0 text-center">
          Un'ultima cosa
        </p>
        <p className="text-[12px] font-jost font-[300] text-center">
          Certifica il tuo profilo caricando la tua carta d'identità per avere
          il badge. Le aziende ti troveranno più facilmente. Una volta caricanto
          il documento riceverai la nostra risposta in 24-48 ore.
        </p>
        <p className="text-[#99B8DD] font-jost text-sm text-center">
          Lo farò in seguito
        </p>

        <InputFile text="Carica la tua carta di identità fronte/retro in formato pdf" />
        <div className="flex justify-center mt-8">
          <Button size="small" handleClick={handlerClick}>
            <span className="text-[12px]">Invia</span>
          </Button>
        </div>
      </div>
    </div>
  );
};

export default StepTwo;
