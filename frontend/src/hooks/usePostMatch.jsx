import { request } from "../utils/axios-utils";
import { useMutation } from "@tanstack/react-query";

const useMatchPostFinal = ({ isCompatible, cardData }) => {
  console.log("useMatchPostFinal - isCompatible:", isCompatible);
  console.log("useMatchPostFinal - cardData:", cardData);
  return request({
    url: `/match/resultCompatibility?isCompatible=${isCompatible}`,
    method: "post",
    data: cardData 
  });
}

export const useMatchPost = () => {
  return useMutation(useMatchPostFinal);
};