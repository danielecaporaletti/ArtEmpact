import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import TextArea from "../../../../../components/JobRequestscomponent/TextArea";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
import { useCreateCard } from "../../../../../contexts/CreateCardContext";
const Page1 = ({ pages, changePage }) => {
  const { title, dispatch } = useCreateCard();

 
  const handleChangeTitle = (event) => {
    if (event.target.value?.length <= 55) {
      dispatch({
        type: "card/create/page1",
        payload: event.target.value,
      });
    }
  };

  const job = {
    title: title,
  };
  return (
    <>
      <div className="bg-white rounded-t-[30px] w-[90%] h-screen mx-auto max-[1024px]:w-full">
        <HeroRequest
          text={"💡 Dai un titolo alla tua richiesta"}
          description={
            "Scrivi in poche parole la tua richiesta, rendila concisa e accattivante, è la prima cosa che il creativo vedrà."
          }
        />
        <div className="flex flex-col justify-center items-center pt-[2.44rem]">
          <TextArea
            value={job.title}
            onChange={handleChangeTitle}
            text={job.title.length}
            number={55}
            maxLength={55}
          />
          <div className="pt-[2.56rem]">
            <ButtonRequest
              text={"Continua"}
              onClick={changePage}
              disabled={pages === 4 || job.title.length === 0}
              hidden={pages === 4}
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default Page1;
