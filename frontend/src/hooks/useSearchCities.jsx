import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import KeycloakConfig from "../config/keycloak-config";

const useSearchCities = (prefix) => {
  const fetchCities = async (prefix) => {
    if (!prefix.length) {
      return null;
    } else {
      try {
        // Ottieni il token da Keycloak
        const token = KeycloakConfig.keycloak.token;

        // Crea un'istanza di axios con le opzioni del proxy
        const instance = axios.create({
          baseURL: `${import.meta.env.VITE_BASE_URL_BACKEND}/`,

          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        // Effettua la richiesta GET
        const response = await instance.get(
          `/comuni/namesByPrefix?prefix=${prefix}`
        );

        // Gestisci la risposta

        return response;
      } catch (error) {}
    }
  };

  const { data: citta } = useQuery(
    ["response", prefix],
    () => fetchCities(prefix),
    false
  );

  return citta;
};

export default useSearchCities;
