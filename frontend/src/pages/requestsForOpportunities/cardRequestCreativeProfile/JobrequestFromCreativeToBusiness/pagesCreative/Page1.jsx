import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import TextArea from "../../../../../components/JobRequestscomponent/TextArea";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
import { useCreateCard } from "../../../../../contexts/CreateCardContext";
import { useFetchCreativeCards } from "../../../../../hooks/useFetchCreativeCards";

const Page1 = ({ pages, changePage }) => {
  const { title, dispatch } = useCreateCard();
  const { data } = useFetchCreativeCards();
  console.log(data)

  const handleChange = (event) => {
    if (event.target.value.length <= 55) {
      dispatch({
        type: "card/create/page1",
        payload: event.target.value,
      });
    }
  };

  const user = {
    title: title,
  };

  return (
    <>
      <div className="bg-white rounded-t-[30px] w-[90%] h-screen mx-auto max-[1024px]:w-full">
        <HeroRequest
          text={"💡 Dai un titolo alla tua richiesta"}
          description={
            "Scrivi in poche parole la tua richiesta, rendila concisa e accattivante, è la prima cosa che l'azienda vedrà."
          }
        />
        <div className="flex flex-col justify-center items-center pt-10">
          <TextArea
            value={user.title}
            onChange={handleChange}
            text={user.title.length}
            number={55}
            maxLength={55}
          />
          <div className="pt-10">
            <ButtonRequest
              text={"Continua"}
              onClick={changePage}
              disabled={pages === 6 || user.title.length === 0}
              hidden={pages === 6}
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default Page1;
