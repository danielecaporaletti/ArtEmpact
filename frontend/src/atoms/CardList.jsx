import React from "react";
import { Link } from "react-router-dom";

const CardList = ({ data }) => {
  return (
    <>
      {data.map((user, i) => {
        return (
          <div className="flex gap-2 w-full py-[22px] px-[25px]" key={i}>
            <div className="shrink-0">
              <img
                className="object-cover w-[76px] h-[76px] rounded-full"
                src={user.img}
                alt="img"
              />
            </div>
            <div className="flex flex-col justify-between w-full">
              <div className="flex flex-col font-jost text-[16px]">
                <span className="font-medium">{user.nome}</span>
                <span className="text-[13px] font-light">{user.settore}</span>
              </div>
              <div className="flex justify-between w-full font-jost font-light text-[16px]">
                <Link to={`/home/creative/profile-preview/${user.id}`}>
                  <span className="text-[#623BFF]">visita il profilo</span>
                </Link>
                <span className=" pr-[23px]  ">messaggio</span>
              </div>
            </div>
          </div>
        );
      })}
    </>
  );
};

export default CardList;
