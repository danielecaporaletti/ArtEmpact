import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import TextArea from "../../../../../components/JobRequestscomponent/TextArea";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
import { useCreateCard } from "../../../../../contexts/CreateCardContext";
import { useState, useEffect } from "react";
const Page4 = ({ changePage }) => {
  const [inputEnabled, setInputEnabled] = useState(true);
  const {
    dispatch,
    minProjectBudget,
    maxProjectBudget,
    identifyEducationBackground,
    identifyExperienceLevel,
    identifyProfessionalRelationship,
    identifyCreativeType,
    description,
    identifyWorkPreference
  } = useCreateCard();

  const user = {
    description: description,
    educationalBackground: identifyEducationBackground,
    experienceLevel: identifyExperienceLevel,
    professionalRelationship: identifyProfessionalRelationship,
    minProjectBudget: minProjectBudget,
    maxProjectBudget: maxProjectBudget,
    identifyCreativeType: identifyCreativeType,
    workpreference: identifyWorkPreference
  };

  const educationalBackgroundObject = [
    {
      id: 0,
      title: "Diploma superiore",
    },
    {
      id: 1,
      title: "Laurea Triennale",
    },
    {
      id: 2,
      title: "Laurea Magistrale",
    },
    {
      id: 3,
      title: "Master",
    },
  ];
  const experienceLevelObject = [
    {
      id: 0,
      title: "Entry level",
    },
    {
      id: 1,
      title: "1-5 anni",
    },
    {
      id: 2,
      title: "5-10 anni",
    },
    {
      id: 3,
      title: "+10 anni",
    },
  ];

  // professional Relationship
  const professionalRelationshipObject = [
    {
      id: 0,
      title: "Tempo pieno",
    },
    {
      id: 1,
      title: "Part time",
    },
    {
      id: 2,
      title: "Contratto",
    },
    {
      id: 3,
      title: "Stage",
    },
    {
      id: 4,
      title: "Partita iva",
    },
  ];
  const creativeType = [
    {
      id: 0,
      title: "Artista visuale",
    },
    {
      id: 1,
      title: "Street Artist",
    },
    {
      id: 2,
      title: "Digital Artist",
    },
    {
      id: 3,
      title: "Scrittore",
    },
    {
      id: 4,
      title: "Musicista",
    },
  ];

  // Manca campo database aspettiamo info da Daniele
  const locality = [
    {
      id: 0,
      title: "Da remoto",
    },
    {
      id: 1,
      title: "Ibrido",
    },
    {
      id: 2,
      title: "In Sede",
    },
  ];

  // Position description
  const handleChange = (event) => {
    if (event.target.value.length <= 280) {
      dispatch({
        type: "card/create/page41",
        payload: event.target.value,
      });
    }
  };

  // Educational Background
  const handleSelect = (index) => {
    dispatch({
      type: "card/create/page42",
      payload: index,
    });
  };

  // Experience Level
  const handleLevelSelect = (index) => {
    dispatch({
      type: "card/create/page43",
      payload: index,
    });
  };

  // Relationship select
  const handleSelectRelationship = (index) => {
    dispatch({
      type: "card/create/page52",
      payload: index,
    });
  };

  // Relationship select
  const handleSelectCreativeType = (index) => {
    dispatch({
      type: "card/create/page48",
      payload: index,
    });
  };

  // Collaboration Type
  const handleCollaboration = (index) => {
    dispatch({
      type: "card/create/page47",
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
  // MinProjectBudget
  const handleChangeMin = (event) => {
    let minInput = event.target.value;
    if (parseInt(minInput) < 0) {
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

  // MaxProjectBudget
  const handleChangeMax = (event) => {
    let maxInput = event.target.value;
    if (parseInt(maxInput) < 0) {
      maxInput = "0";
    }
    dispatch({
      type: "card/create/page54",
      payload: maxInput,
    });
    updateBudgetValid(minProjectBudget, maxInput);
  };

  return (
    <div className="bg-white rounded-t-[30px] xl:w-[90%] 2xl:h-screen mx-auto w-full h-auto overflow-y-hidden">
      <HeroRequest
        description={
          "Ogni sezione è obbligatoria e serve a dare tutte le informazioni essenziali al professionista."
        }
        text={"👤  Descrivi cosa cerchi nel professionista"}
      />
      <div className="flex flex-col justify-center items-center pt-0">
        <div className="flex flex-col">
          <span className="text-left lg:text-[16px] text-[1rem] text-slate font-normal leading-normal text-[#2F2F2F] pt-[1.38rem] pl-[1rem] xl:pl-0">
            ✏️ Descrivi l'offerta di lavoro
          </span>
          <TextArea
            value={user.description}
            onChange={handleChange}
            text={user.description.length}
            number={280}
            maxLength={280}
          />
        </div>
      </div>
      <div className="flex justify-start ml-[10%] pt-[1.38rem] xl:ml-[17rem]">
        <p className="text-left text-[1rem]">
          📚 Scegli la formazione deve avere il tuo collaboratore.
        </p>
      </div>
      <div className="flex justify-center gap-[1.19rem] items-center xl:gap-[2.88rem] flex-wrap pt-[0.56rem] px-2">
        {educationalBackgroundObject.map((title, index) => (
          <button
            key={title.id}
            className={`text-[0.8rem] shadow-btn-vert rounded-full text-primary-color border-none text-center w-[10rem] ${
              index === user.educationalBackground
                ? "bg-primary-color text-white"
                : "bg-white text-primary-color"
            }`}
            onClick={() => handleSelect(title.id)}
          >
            {title.title}
          </button>
        ))}
      </div>
      <div className="flex justify-start ml-[10%] pt-[1.38rem] xl:ml-[17rem]">
        <p className="text-left text-[1rem]">
          💼 Scegli quanta esperienza deve avere il tuo collaboratore.
        </p>
      </div>
      <div className="flex gap-[0.62rem] xl:gap-[2.88rem] flex-wrap justify-center items-center pt-[0.56rem]">
        {experienceLevelObject.map((title, index) => (
          <button
            key={title.id}
            className={`text-[0.8rem] shadow-btn-vert rounded-full text-primary-color border-none text-center w-[10rem] ${
              index === user.experienceLevel
                ? "bg-primary-color text-white"
                : "bg-white text-primary-color"
            }`}
            onClick={() => handleLevelSelect(title.id)}
          >
            {title.title}
          </button>
        ))}
      </div>
      <div className="flex justify-start ml-[10%] pt-[1.38rem] xl:ml-[17rem]">
        <p className="text-left text-[1rem]">
          👥 Indica il tipo di rapporto professionale che verrà instaurato per
          questa collaborazione
        </p>
      </div>
      <div className="flex xl:gap-[2.88rem] gap-[0.94rem]  flex-wrap justify-center items-center pt-[0.56rem]">
        {professionalRelationshipObject.map((title, index) => (
          <button
            key={title.id}
            className={`text-[0.8rem] shadow-btn-vert rounded-full text-primary-color border-none text-center w-[10rem] ${
              index === user.professionalRelationship
                ? "bg-primary-color text-white"
                : "bg-white text-primary-color"
            }`}
            onClick={() => handleSelectRelationship(title.id)}
          >
            {title.title}
          </button>
        ))}
      </div>
      <div className="flex justify-start ml-[10%] pt-[1.38rem] xl:ml-[17rem]">
        <p className="text-left text-[1rem]">
          🎨 Che tipo di artista stai cercando?
        </p>
      </div>
      <div className="flex xl:gap-[2.88rem] gap-[0.94rem] flex-wrap justify-center items-center pt-[0.56rem]">
        {creativeType.map((title, index) => (
          <button
            key={title.id}
            className={`text-[0.8rem] shadow-btn-vert rounded-full text-primary-color border-none text-center w-[10rem] ${
              user.identifyCreativeType === index
                ? "bg-primary-color text-white"
                : "bg-white text-primary-color"
            }`}
            onClick={() => handleSelectCreativeType(title.id)}
          >
            {title.title}
          </button>
        ))}
      </div>
      <div className="flex justify-start ml-[10%] pt-[1.38rem] xl:ml-[17rem]">
        <p className="text-left text-[1rem]">
          💸 Indica il budget destinato al creativo per questo progetto
        </p>
      </div>
      <div className="flex gap-[1.6rem] justify-center items-center pt-[0.56rem]">
        <div className="flex flex-col justify-between">
          <input
            id="min"
            min={0}
            placeholder="€____,______"
            className="text-[0.8rem] w-[4.5rem] shadow-btn-vert text-center rounded-full px-2"
            type="number"
            step="0.01"
            value={user.minProjectBudget}
            onChange={handleChangeMin}
          />
          <label htmlFor="min" className="text-[0.8rem]">
            Minimo
          </label>
        </div>
        <div className="flex flex-col justify-between">
          <input
            min={0}
            id="max"
            placeholder="€____,______"
            className="text-[0.8rem] w-[4.5rem] shadow-btn-vert text-center rounded-full px-2"
            type="number"
            step="0.01"
            value={user.maxProjectBudget}
            onChange={handleChangeMax}
          />
          <label htmlFor="max" className="text-[0.8rem]">
            Massimo
          </label>
        </div>
      </div>
      <div className="flex justify-start ml-[10%] pt-[1.38rem] xl:ml-[17rem]">
        <p className="text-left text-[1rem]">📍 Luogo di lavoro</p>
      </div>
      <div className="flex gap-[1.6rem] flex-wrap justify-center items-center pt-[0.56rem]">
        {locality.map((title, index) => (
          <button
            key={title.id}
            className={`text-[0.8rem] shadow-btn-vert rounded-full text-primary-color border-none text-center w-[10rem] ${
              index === user.workpreference
                ? "bg-primary-color text-white"
                : "bg-white text-primary-color"
            }`}
            onClick={() => handleCollaboration(title.id)}
          >
            {title.title}
          </button>
        ))}
      </div>
      <div className="pt-[1rem] pb-10">
        <ButtonRequest
          text={"Continua"}
          onClick={changePage}
          disabled={!inputEnabled || user.description.length === 0 || user.educationalBackground === null || user.experienceLevel === null || user.professionalRelationship === null || user.identifyCreativeType === null || user.workpreference === null}
        />
      </div>
    </div>
  );
};

export default Page4;
