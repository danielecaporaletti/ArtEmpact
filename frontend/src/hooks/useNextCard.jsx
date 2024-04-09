import { request } from "../utils/axios-utils";
import { useQuery } from "@tanstack/react-query";

const fetchNextcard = () => {
    return request({
      url: `/match/nextCard`,
      method: "get",
    });
  }

export const useFetchNextcard = (fetchKey) => {
  return useQuery(["algorithm"], fetchNextcard)
};
