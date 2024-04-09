import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import TextArea from "../../../../../components/JobRequestscomponent/TextArea";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
import { useCreateCard } from "../../../../../contexts/CreateCardContext";

const Page3 = ({ pages, changePage }) => {
  const { companyVisionMission, dispatch } = useCreateCard();
  const handleChange = (event) => {
    if (event.target.value.length <= 280) {
      dispatch({
        type: "card/create/page46",
        payload: event.target.value,
      });
    }
  };

  const mission = {
    companyVisionMission: companyVisionMission,
  };
  return (
    <>
      <div className="bg-white rounded-t-[30px] w-[90%] h-screen mx-auto max-[1024px]:w-full">
        <HeroRequest
          description={
            "Descrivi la tua azienda a parole, aggiungi \n tutte quelle informazioni che non sei riuscito a mettere nel video."
          }
          text={"🚀 Descrivi la tua azienda, vision e mission"}
        />
        <div className="flex flex-col justify-center items-center pt-10">
          <TextArea
            value={mission.companyVisionMission}
            placeholder={"💡 Dai un titolo alla tua richiesta"}
            onChange={handleChange}
            text={mission.companyVisionMission.length}
            number={280}
            maxLength={280}
          />
          <div className="pt-10">
            <ButtonRequest
              text={"Continua"}
              onClick={changePage}
              disabled={
                pages === 4 || mission.companyVisionMission.length === 0
              }
              hidden={pages === 4}
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default Page3;
