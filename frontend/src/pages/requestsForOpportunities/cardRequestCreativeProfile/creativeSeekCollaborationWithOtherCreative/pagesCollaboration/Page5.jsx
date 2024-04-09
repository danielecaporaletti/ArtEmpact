import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
import CardTest from "../../../../../components/JobRequestscomponent/CardTest";
import background from "../../../../home/homeCreative/man.png";
import { useCreateCard } from "../../../../../contexts/CreateCardContextCollaboration";
import { usePostCardFinal } from "../../../../../hooks/usePostCardFinalCollaborationCreative";
const Page5 = ({ pages, changePage }) => {
  const colors = [
    "gradient-sky",
    "gradient-purple-layout",
    "blue-layout",
    "orange-layout",
    "purple-bg",
  ];

  const { 
    cardColor, 
    dispatch, 
    title,
    description,
    minProjectBudget,
    maxProjectBudget,
    identifyEducationBackground,
    identifyExperienceLevel,
    identifyProfessionalRelationship,
    identifyCreativeType,
    personalVisionMission,
    identifyWorkPreference,
    } = useCreateCard();
  const { mutate, isLoading } = usePostCardFinal();
  const submitForm = () => {
    try {
      const finalObject = {
        title: title,
        description: description,
        minProjectBudget: Math.floor(minProjectBudget),
        maxProjectBudget: Math.floor(maxProjectBudget),
        cardColor: "6",
        identifyEducationBackground: identifyEducationBackground + 1,
        identifyExperienceLevel: identifyExperienceLevel + 1,
        identifyProfessionalRelationship: identifyProfessionalRelationship + 1,
        identifyWorkPreference: identifyWorkPreference + 1,
        personalVisionMission: personalVisionMission,
        identifyCreativeType: identifyCreativeType + 1,
        creativeSeeksCollaborationLocations: [
          {
            "city": "Brescia",
            "province": "BS"
          },
          {
            "city": "Milano",
            "province": "MI"
          }
        ]
      };
      mutate(finalObject);
      console.log(finalObject)
      changePage();
      } catch (error) {
      console.error("Error submitting form:", error)
    }
  }

  const changeColor = (newColor) => {
    dispatch({
      type: "card/create/page6",
      payload: newColor,
    });
  };
  const user = {
    name: "Medi_srl",
    description: "Azienda corporate",
    hook: "Sta cercando",
    object: "Scultore per",
    core: "un progetto di un cliente",
    cardColor: cardColor,
  };
  return (
    <>
      <div
        className={`bg-white rounded-t-[30px] w-[90%] h-auto mx-auto max-[1024px]:w-full overflow-x-hidden`}
      >
        <HeroRequest text={"🎨 Personalizza la tua richiesta"} />
        <div className="flex flex-col justify-center items-center">
          <CardTest
            color={user.cardColor}
            background={background}
            name={user.name}
            description={user.description}
            hook={user.hook}
            object={user.object}
            core={user.core}
          ></CardTest>
          <div className="flex flex-col xl:flex xl:justify-center xl:items-center mx-auto pt-[3rem] w-[90%]">
          <h3 className="text-black sm:text-center ml-2 font-semibold font-['Montserrat'] text-[1.25rem] leading-normal">
            Scegli la grafica che preferisci per la tua richiesta
          </h3>
          <div className="pt-[2.12rem] sm:justify-center flex justify-center">
            <ul className="flex whitespace-nowrap list-none gap-[1.25rem] overflow-x-auto">
              {colors.map((palette, index) => (
                <li key={index} className="inline-block pl-2">
                  <button
                    className={`rounded-full w-[5.25rem] lg:w-[5.25rem] lg:h-[5.25rem] h-[5.25rem] max-[280px]:w-[5rem] ${palette} shadow-btn-colors`}
                    onClick={() => changeColor(palette)}
                  ></button>
                </li>
              ))}
            </ul>
          </div>
        </div>
        </div>

        <div className="pb-[2.31rem] pt-[4rem]">
          <ButtonRequest text={"Pubblica"} onClick={() => submitForm()} disabled={isLoading} />
        </div>
      </div>
    </>
  );
};

export default Page5;
