import { request } from "../utils/axios-utils";
import { useQuery } from "@tanstack/react-query";

const fetchCreativeCards = () => {
    return request({
      url: `/profile/creative/creativeSeeksBusiness`,
      method: "get",
    });
  }

export const useFetchCreativeCards = () => {
  return useQuery(["cards"], fetchCreativeCards)
};
