import { useMutation } from "@tanstack/react-query";
import { request } from "../utils/axios-utils";

const addCardData = (data) => {
  return request({
    url: "/profile/creative/creativeSeeksBusiness",
    method: "post",
    data: data,
  });
};
export const usePostCardFinal = () => {
  return useMutation(addCardData);
};
