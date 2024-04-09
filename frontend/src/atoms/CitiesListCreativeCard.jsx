import React from "react";

const CitiesListCreativeCard = ({ cities, onclick }) => {
  if (cities?.data?.length === 0) {
    return (
      <div className="border w-[18rem] lg:w-[22.75rem] h-auto shadow-md z-10 bg-white rounded-xl  px-3 py-2">
        <p className="text-sm text-gray-700 my-2">Nessuna corrispondenza</p>
      </div>
    );
  }
  return (
    <div className="border w-[18rem] lg:w-[22.75rem] max-h-60 shadow-md overflow-y-scroll scrollbar-hide z-10 bg-white rounded-xl py-2  px-3">
      {cities?.data?.map((el, i) => {
        return (
          <p
            className="text-sm my-2 text-gray-700 hover:text-black hover:bg-gray-200"
            onClick={() => onclick(el.city, el.province)}
            key={i}
          >
            {el.city} - {el.province}
          </p>
        );
      })}
    </div>
  );
};

export default CitiesListCreativeCard;
