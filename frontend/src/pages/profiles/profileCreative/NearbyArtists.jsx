import React from "react";
import NearbyCard from "../../../components/NearbyCard";
import { users } from "../../../data/UserData";

const NearbyArtists = () => {
  return (
    <div
      className="bg-[#ebfdff] flex justify-center
   bg-[url('./assetTemporanei/texture.png')] bg-no-repeat bg-cover min-h-screen p-4 "
    >
      <NearbyCard data={users} />
    </div>
  );
};

export default NearbyArtists;
