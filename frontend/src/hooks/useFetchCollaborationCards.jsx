import { request } from "../utils/axios-utils";
import { useQuery } from "@tanstack/react-query";

const fetchCollaborationCards = () => {
    return request({
      url: `/profile/creative/creativeSeeksCollaboration`,
      method: "get",
    });
  }

export const useFetchCollaborationCards = () => {
  return useQuery(["cards"], fetchCollaborationCards)
};
