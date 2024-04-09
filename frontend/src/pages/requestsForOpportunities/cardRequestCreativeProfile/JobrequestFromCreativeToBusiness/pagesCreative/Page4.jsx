import { useCreateCard } from "../../../../../contexts/CreateCardContext";
import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import TextArea from "../../../../../components/JobRequestscomponent/TextArea";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
const Page4 = ({ pages, changePage }) => {
  const { description, dispatch, identifyExperienceLevel, identifyEducationBackground } =
    useCreateCard();

  const user = {
    description: description,
    experienceLevel: identifyExperienceLevel,
    educationalBackground: identifyEducationBackground,
  };
  const educationalBackgroundObject = [
    {
      id: 0,
      name: "Diploma scuola superiore",
    },
    {
      id: 1,
      name: "Laurea Triennale",
    },
    {
      id: 2,
      name: "Laurea Magistrale",
    },
    {
      id: 3,
      name: "Master",
    },
  ];

  const experienceLevelObject = [
    {
      id: 0,
      name: "Entry Level",
    },
    {
      id: 1,
      name: "1-5 anni",
    },
    {
      id: 2,
      name: "5-10 anni",
    },
    {
      id: 3,
      name: "+10 anni",
    },
  ];
  const handleChange = (event) => {
    if (event.target.value.length <= 280) {
      dispatch({
        type: "card/create/page41",
        payload: event.target.value,
      });
    }
  };

  const handleSelect = (index) => {
    dispatch({
      type: "card/create/page42",
      payload: index,
    });
  };

  const handleLevelSelect = (index) => {
    dispatch({
      type: "card/create/page43",
      payload: index,
    });
  };

  return (
    <>
      <div className="bg-white rounded-t-[30px] w-[90%] h-screen mx-auto max-[1024px]:w-full">
        <HeroRequest
          text={"👤 A proposito di me"}
          description={
            "Descrivi te stesso, indica la tua formazione e il tuo background in modo semplice e chiaro"
          }
        />
        <div className="flex flex-col justify-center items-center pt-0">
          <TextArea
            value={user.description}
            onChange={handleChange}
            text={user.description.length}
            number={280}
            maxLength={280}
          />
        </div>
        <div className="flex justify-start ml-[10%] pt-5 xl:ml-[20%]">
          <p className="text-left text-[16px]">📚 Qual è la tua formazione?</p>
        </div>
        <div className="flex justify-center gap-6 items-center flex-wrap pt-2 px-2">
          {educationalBackgroundObject.map((value, index) => (
            <button
              key={value.id}
              className={`text-[0.8rem] shadow-btn-vert rounded-full text-primary-color border-none text-center w-[10rem] ${
                index === user.educationalBackground
                  ? "bg-primary-color text-white"
                  : "bg-white text-primary-color"
              }`}
              onClick={() => handleSelect(value.id)}
            >
              {value.name}
            </button>
          ))}
        </div>
        <div className="flex justify-start ml-[10%] pt-5 xl:ml-[20%]">
          <p className="text-left text-[16px]">
            💼 Qual è il tuo livello di esperienza?
          </p>
        </div>
        <div className="flex justify-center gap-6 items-center flex-wrap pt-2 px-2">
          {experienceLevelObject.map((value, index) => (
            <button
              key={value.id}
              className={`text-[0.8rem] shadow-btn-vert rounded-full text-primary-color border-none text-center w-[6rem] ${
                index === user.experienceLevel
                  ? "bg-primary-color text-white"
                  : "bg-white text-primary-color"
              }`}
              onClick={() => handleLevelSelect(value.id)}
            >
              {value.name}
            </button>
          ))}
        </div>
        <div className="pt-8 2xl:pt-5">
          <ButtonRequest
            text={pages === 5 ? "Pubblica" : "Continua"}
            onClick={changePage}
            disabled={pages === 6 || user.description.length === 0 || user.educationalBackground === null || user.experienceLevel === null}
            hidden={pages === 6}
          />
        </div>
      </div>
    </>
  );
};

export default Page4;
