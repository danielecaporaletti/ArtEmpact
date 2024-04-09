import axios from "axios";
import KeycloakConfig from "../config/keycloak-config";

const client = axios.create({ baseURL: import.meta.env.VITE_BASE_URL_BACKEND });
const s3 = axios.create({ baseURL: import.meta.env.VITE_BASE_URL_S3 });
const geo = axios.create({ baseURL: import.meta.env.VITE_BASE_URL_GEO });

// Funzione per aggiornare il token di autorizzazione per le istanze Axios
const updateAuthorizationHeader = (service) => {
  const token = KeycloakConfig.keycloak.token;
  service.defaults.headers.common.Authorization = `Bearer ${token}`;
};

// Gestore di richieste generico
const makeRequest = (service, options) => {
  updateAuthorizationHeader(service); // Aggiorna l'header di autorizzazione con il token più recente
  
  const onSuccess = (response) => response;
  const onError = (error) => {
    // Gestisci l'errore qui (potresti voler rinnovare il token, registrare l'errore, ecc.)
    return Promise.reject(error);
  };

  return service(options).then(onSuccess).catch(onError);
};

// Esempi di funzioni esportate per effettuare richieste ai microservizi
export const request = (options) => makeRequest(client, options);
export const requestS3 = (options) => makeRequest(s3, options);
export const requestGeo = (options) => makeRequest(geo, options);
