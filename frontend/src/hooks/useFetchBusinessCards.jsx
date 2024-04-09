import { request } from "../utils/axios-utils";
import { useQuery } from "@tanstack/react-query";

const fetchBusinessCards = () => {
    return request({
      url: `/profile/business/businessSeeksCreative`,
      method: "get",
    });
  }

export const useFetchBusinessCards = () => {
  return useQuery(["cards"], fetchBusinessCards)
};
