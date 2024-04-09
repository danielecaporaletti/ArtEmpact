import React, { useState } from "react";
import Button from "../../atoms/Button";
import InputField from "../../atoms/inputField";
import InputFile from "../../atoms/InputFile";
import { useFetchCities } from "../../hooks/useFetchCities";
import CitiesList from "../../atoms/CitiesList";

const StepOne = ({
  page,
  setPage,
  data,
  isTrue,
  input,
  setInput,
  city,
  setCity,
  setProvince,
}) => {
  const inputValue = (value) => {
    if (value === "stageName") {
      return input.stageName;
    } else {
      return input.phone;
    }
  };
  const [, setFile] = useState("");

  const [prefix, setPrefix] = useState("");

  const { data: citta, isLoading } = useFetchCities(prefix);

  const handleCity = (e) => {
    setPrefix(e.target.value);
    setCity(e.target.value);
  };

  const handleChange = (e) => {
    setInput({ ...input, [e.target.name]: e.target.value });
  };

  function test(name, id) {
    setPrefix("");
    setCity(name);
    setProvince(id);
  }
  const handlerClick = () => {
    if (!input.phone || !city) {
      alert("form");
    } else {
      setPage(page + 1);
    }
  };

  return (
    <div className="relative flex flex-col md:gap-4 justify-between w-screen md:w-[390px]">
      <div className="flex flex-col justify-start gap-2 w-full h-[565px] border p-4 pb-8 bg-white rounded-t-3xl z-10">
        <p className="text-[30px] font-bold font-montserrat sm:mt-2 mt-8 mb-6 sm:mb-0 text-center">
          Ci siamo quasi!
        </p>

        {data?.map((el, i) => {
          return (
            <InputField
              required={false}
              id={el.name}
              key={i}
              type={el.type}
              value={inputValue(el.value)}
              label={el.label}
              name={el.value}
              text={el.placeHolder}
              onChange={handleChange}
            />
          );
        })}
        <div className="relative">
          <InputField
            value={city}
            type="text"
            name="city"
            onChange={handleCity}
            label={"Città"}
          />
          {citta && (
            <CitiesList
              cities={citta.data}
              loading={isLoading}
              onclick={test}
            />
          )}
        </div>
        <InputFile
          onChange={(e) => setFile(e.target.files[0])}
          text="Carica la tua carta di identità fronte/retro in formato pdf"
        />

        {isTrue && (
          <p className="text-[12px] font-jost font-[300]">
            Certifica il tuo profilo caricando la tua carta d'identità per avere
            il badge. Le aziende ti troveranno più facilmente. Una volta
            caricanto il documento riceverai la nostra risposta in 24-48 ore.
          </p>
        )}
        <div className="flex justify-center mt-8">
          <Button handleClick={handlerClick} size={"small"}>
            <span className="text-[12px]">Continua</span>
          </Button>
        </div>
      </div>
    </div>
  );
};

export default StepOne;
