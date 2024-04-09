//Page1.jsx
import IconVerified from "../../../icons/IconVerified";
import background from "./man.png";
import { useFetchNextcard } from "../../../hooks/useNextCard";
// Chiamata GET

// Creazione oggetto da inserire nella pagina
// Inserire i parametri dell'oggetto nella pagian

function Page1() {
  const { data, isLoading, isError, error } = useFetchNextcard();

  if (isLoading) {
    return <div>Caricamento in corso...</div>;
  }

  if (isError) {
    return <div>Errore nel caricamento: {error.message}</div>;
  }

  console.log(data);
  
  const dataPage1 = {
    titolo: data.data.compatibilityCard.title,
    description: "Azienda Corporate",
    hook: "Sta cercando",
    object: "Scultore per",
    core: "progetto di un cliente",
  };

  return (
    <>
      <div
        className="w-[9.375rem] h-[9.375rem] bg-cover self-center"
        style={{
          backgroundImage: `url(${background})`,
        }}
      ></div>
      <div className="w-auto flex flex-col relative">
        <div className="flex flex-row justify-center">
          <div className="h-[3.0625rem] text-white text-[2.5rem] font-montserrat font-bold tracking-[.5px] underline underline-offset-[5px] decoration-[3px] text-center leading-none">
            {dataPage1.titolo}
          </div>
          <div className="h-4 aspect-w-1 aspect-h-1 flex justify-center">
            <IconVerified color={`var(--color-primary-color)`} />
          </div>
        </div>
        <div className="mt-1.25 h-5 text-white font-montserrat font-normal text-sm tracking-wide leading-normal text-center">
          {dataPage1.description}
        </div>
      </div>
      <div className="flex flex-col items-center justify-center mt-6.8 text-white">
        <div className="font-montserrat h-30 text-xs font-normal">
          <p>{dataPage1.hook}</p>
        </div>
        <div className="text-center text-shadow-sm font-montserrat text-lg font-bold">
          <p>{dataPage1.object}</p>
          <p>{dataPage1.core}</p>
        </div>
      </div>
    </>
  );
}

export default Page1;
