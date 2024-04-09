import { useMutation } from "@tanstack/react-query";
import { request } from "../utils/axios-utils";

const addProjectData = (data) => {
  return request({
    url: "/profile/creative/projectCreative",
    method: "post",
    data,
  });
};
export const usePostProjects = () => {
  return useMutation(addProjectData);
};
