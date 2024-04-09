import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import TextArea from "../../../../../components/JobRequestscomponent/TextArea";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
import { useCreateCard } from "../../../../../contexts/CreateCardContext";
import { useState, useEffect } from "react";

const Page5 = ({ pages, changePage }) => {
  const [inputEnabled, setInputEnabled] = useState(true);
  const {
    positionDescription,
    minProjectBudget,
    maxProjectBudget,
    identifyProfessionalRelationship,
    dispatch,
  } = useCreateCard();

  const user = {
    description: positionDescription,
    minBudget: minProjectBudget,
    maxBudget: maxProjectBudget,
    professionalRelationship: identifyProfessionalRelationship,
  };
  const professionalRelationshipObject = [
    {
      id: 0,
      name: "Tempo pieno",
    },
    {
      id: 1,
      name: "Part Time",
    },
    {
      id: 2,
      name: "Contratto",
    },
    {
      id: 3,
      name: "Stage",
    },
    {
      id: 4,
      name: "Partita iva",
    },
  ];
  
  const handleChange = (event) => {
    if (event.target.value.length <= 280) {
      dispatch({
        type: "card/create/page51",
        payload: event.target.value,
      });
    }
  };

  const handleSelect = (index) => {
    dispatch({
      type: "card/create/page52",
      payload: index,
    });
  };
  const updateBudgetValid = (min, max) => {
    if (parseInt(min) === 0 && parseInt(max) === 0) {
      setInputEnabled(false)
    } else if (parseInt(min) > parseInt(max) || max === "" || min === "") {
      setInputEnabled(false)
    } else {
      setInputEnabled(true)
    }
  }

  useEffect(() => {
    updateBudgetValid(minProjectBudget, maxProjectBudget)
  }, [minProjectBudget, maxProjectBudget])
  const handleChangeMin = (event) => {
    let minInput = event.target.value;
    if (parseFloat(minInput) < 0) {
      minInput = "0";
    } else if (parseInt(minInput) > parseInt(maxProjectBudget) && parseInt(maxProjectBudget) > 0) {
      minInput = maxProjectBudget;
    } 
    dispatch({
      type: "card/create/page53",
      payload: minInput,
    });
    updateBudgetValid(minInput, maxProjectBudget);
  };

  const handleChangeMax = (event) => {
    let maxInput = event.target.value;
    if (parseFloat(maxInput) < 0) {
      maxInput = "0";
    }
    dispatch({
      type: "card/create/page54",
      payload: maxInput,
    });
    updateBudgetValid(maxInput, minProjectBudget);
  };

  return (
    <>
      <div className="bg-white rounded-t-[30px] w-[90%] h-screen mx-auto max-[1024px]:w-full">
        <HeroRequest
          text={"✏️ Descrizione della posizione"}
          description={
            "Descrivi il tipo di lavoro che stai cercando. Sii specifico e chiaro"
          }
        />
        <div className="flex flex-col justify-center items-center pt-3">
          <div className="flex flex-col">
            <TextArea
              value={user.description}
              onChange={handleChange}
              text={user.description.length}
              number={280}
              maxLength={280}
            />
          </div>
        </div>
        <div className="flex justify-start sm:ml-[28%] ml-[5%]">
          <p className="text-left pt-5 text-[1rem] text-slate leading-normal">
            👥 Indica il tipo di rapporto professionale che vuoi instaurare
          </p>
        </div>
        <div className="flex gap-4 flex-wrap justify-center items-center pt-4 px-2">
          {professionalRelationshipObject.map((value, index) => (
            <button
              key={value.id}
              className={`text-[0.8rem] shadow-btn-vert rounded-full text-primary-color border-none text-center w-[6rem] ${
                index === user.professionalRelationship
                  ? "bg-primary-color text-white"
                  : "bg-white text-primary-color"
              }`}
              onClick={() => handleSelect(value.id)}
            >
              {value.name}
            </button>
          ))}
        </div>
        <div className="flex justify-start sm:ml-[28%] ml-[5%]">
          <p className="text-left pt-5 text-[1rem] text-slate leading-normal">
            💸 Indica il range di compenso che desideri
          </p>
        </div>
        <div className="flex gap-10 justify-center items-center pt-4">
          <div className="flex flex-col justify-between">
            <input
              id="min"
              min={0}
              placeholder="€____,______"
              className="text-[0.8rem] w-[4.5rem] shadow-btn-vert text-left rounded-full px-2"
              type="number"
              step="0.01"
              onChange={handleChangeMin}
              value={user.minBudget}
            />
            <label htmlFor="min" className="text-[0.8rem]">
              Minimo
            </label>
          </div>
          <div className="flex flex-col justify-between">
            <input
              id="max"
              min={0}
              placeholder="€____,______"
              className="text-[0.8rem] w-[4.5rem] shadow-btn-vert text-left rounded-full px-2"
              type="number"
              step="0.01"
              onChange={handleChangeMax}
              value={user.maxBudget}
            />
            <label htmlFor="max" className="text-[0.8rem]">
              Massimo
            </label>
          </div>
        </div>
        <div className="pt-5">
          <ButtonRequest
            text={"Continua"}
            onClick={changePage}
            disabled={pages === 6 || user.description.length === 0 || !inputEnabled || user.professionalRelationship === null}
            hidden={pages === 6}
          />
        </div>
      </div>
    </>
  );
};

export default Page5;
