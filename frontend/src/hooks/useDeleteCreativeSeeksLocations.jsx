import { request } from "../utils/axios-utils";
import { useMutation } from "@tanstack/react-query";

const deleteCities = (id) => {
  if (!id) {
    return null;
  } else {
    return request({
      url: `/profile/creative/creativeSeeksBusiness/creativeSeeksBusinessLocation?id=${id}`,
      method: "delete",
    })
  }
};

export const useDeleteCreativeSeeksCities = () => {
  return useMutation(deleteCities);
};