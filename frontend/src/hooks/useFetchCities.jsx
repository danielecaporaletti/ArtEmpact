import { requestGeo } from "../utils/axios-utils";
import { useQuery } from "@tanstack/react-query";

const fetchCities = (prefix) => {
  if (!prefix) {
    return null;
  } else {
    return requestGeo({
      url: `/search?prefix=${prefix}`,
      method: "get",
    });
  }
};

export const useFetchCities = (prefix) => {
  return useQuery(["cities", prefix], () => fetchCities(prefix), {
    enabled: !!prefix, // Questo assicura che la query venga eseguita solo se il prefix è presente
  });
};
