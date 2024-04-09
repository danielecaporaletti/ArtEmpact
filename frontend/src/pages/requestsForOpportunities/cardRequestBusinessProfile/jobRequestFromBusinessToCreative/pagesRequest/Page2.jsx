import InputFile from "../../../../../components/JobRequestscomponent/InputFile";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import {useCreateCard } from "../../../../../contexts/CreateCardContext";
const Page2 = ({ pages, changePage, show }) => {
  const { dispatch, video1 } = useCreateCard();

  function uploadVideo(event) {
    dispatch({
      type: "card/create/page2",
      payload: event.target.files[0],
    });
  }


  return (
    <>
      <div
        className="bg-white rounded-t-[30px] w-full h-screen mx-auto xl:w-[90%] flex flex-col overflow-x-hidden"
        hidden={!show ? "hidden" : ""}
      >
        <HeroRequest
          text={"📹 Inserisci un breve video"}
          description={
            "Le richieste con le hanno il doppio del successo delle altre. Carica un video di 15-20 secondi in cui racconti la tua azienda."
          }
        />
        <InputFile
          type={"file"}
          accept="video/*"
          text={"Carica il video"}
           onChange={uploadVideo}
          video={video1}
        />
        <div className="pt-[5.12rem] lg:pt-[2.80rem] smallphone">
          <ButtonRequest
            text={"Continua"}
            onClick={changePage}
            disabled={pages === 4}
            hidden={pages === 4}
          />
        </div>
        <style>
          {`
              @media (max-width: 280px) {
                .smallphone {
                  padding-top: 0rem
                }
              }
            `}
        </style>
      </div>
    </>
  );
};

export default Page2;
