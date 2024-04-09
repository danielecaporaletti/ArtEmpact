import HeroRequest from "../../../../../components/JobRequestscomponent/HeroRequest";
import Searchbar from "../../../../../components/JobRequestscomponent/Searchbar";
import ButtonRequest from "../../../../../components/JobRequestscomponent/ButtonRequest";
import { useCreateCard } from "../../../../../contexts/CreateCardContext";
import { useState } from "react";
import React from "react";
import { useFetchCities } from "../../../../../hooks/useFetchCities";
import CitiesListCreativeCard from "../../../../../atoms/CitiesListCreativeCard";

function Page3({ pages, changePage }) {
  const { dispatch,  creativeSeeksBusinessLocations, identifyWorkPreference } = useCreateCard();

  const [prefix, setPrefix] = useState("");
  const { data: citta, isLoading } = useFetchCities(prefix);


  const workPreferenceObject = [
    {
      id: 0,
      name: "In sede"
    },
    {
      id: 1,
      name: "Ibrido"
    },
    {
      id: 2,
      name: "Da remoto"
    }
  ]

  const searchUpdate = (e) => {
    setPrefix(e.target.value);
  };

  function clickSelectFromList(name, id) {
    setPrefix("");
    const CityisAlreadyIncluded = creativeSeeksBusinessLocations.some(
      (city) => city.province === id && city.city === name 
    );
    const maxCities = creativeSeeksBusinessLocations.length >= 3;
    // Check if the city with the same province is already included
    if (!CityisAlreadyIncluded && !maxCities) {
   dispatch({
     type: "card/create/page3",
     payload: {city: name, province: id },
   })
  } 
}

  const handleDeleteCity = (id) => {
      dispatch({
        type: "card/create/page3/delete",
        payload: id
      })
  }

  const handleWorkPreference = (index) => {
    dispatch({
      type: "card/create/page47",
      payload: index,
    })
  }
  return (
    <div className="bg-white rounded-t-[30px] w-[90%] h-screen mx-auto max-[1024px]:w-full">
      <HeroRequest
        text={"📍 Dove vuoi cercare lavoro?"}
        description={
          "Cerca le località in cui sei disponibile a lavorare e indica se vuoi farlo in sede, ibrido o da remoto."
        }
      />
      <div className="pt-10 flex flex-col justify-center items-center">
        <div className="flex justify-center">
          <Searchbar
            value={prefix}
            onChange={searchUpdate}
            placeholder={"Roma"}
          />
        </div>
        <div className="w-[90%] flex justify-center items-center mx-auto">
          <ul className="flex flex-wrap lg:gap-6 gap-2"></ul>
        </div>

        {citta && (
          <CitiesListCreativeCard
            cities={citta}
            loading={isLoading}
            onclick={clickSelectFromList}
          />
        )}

        <div className="w-[50%] gap-2 flex justify-center items-center mt-6 flex-wrap">
          {creativeSeeksBusinessLocations.map((city, index) => (
            <div
              key={index}
              className="flex gap-2 text-[0.8rem] shadow-btn-vert rounded-full text-white bg-primary-color px-2 border-none text-center w-[auto]"
              >
              <p>{city.city}</p>
              <div onClick={() => handleDeleteCity(city)}>x</div>
            </div>
          ))}
        </div>
        <div className="w-[50%] gap-2 flex justify-center items-center mt-8 flex-wrap">
          {workPreferenceObject.map((work, index) => (
            <button
              key={work.id}
              className={`flex gap-2 text-[0.8rem] shadow-btn-vert rounded-full ${index === identifyWorkPreference ? "text-white bg-primary-color" : "text-primary-color bg-white"} px-2 border-none text-center w-[auto]`}
              onClick={() => handleWorkPreference(work.id)}>
              <span>{work.name}</span>
            </button>
          ))}
        </div>
      </div>
      <div className="pt-10 pb-10">
        <ButtonRequest
          text={pages === 5 ? "Pubblica" : "Continua"}
          onClick={changePage}
          disabled={creativeSeeksBusinessLocations.length === 0 || identifyWorkPreference === -1}
          hidden={pages === 6}
        />
      </div>
    </div>
  );
}

export default Page3;
