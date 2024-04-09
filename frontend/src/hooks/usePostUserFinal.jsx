import { useMutation } from "@tanstack/react-query";
import { request } from "../utils/axios-utils";

const addUserData = (data) => {
  return request({ url: "/finalRegistrationStep", method: "post", data });
};
export const usePostUserFinal = () => {
  return useMutation(addUserData);
};
