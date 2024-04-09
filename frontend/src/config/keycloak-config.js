import axios from "axios";
import Keycloak from 'keycloak-js';

const keycloakConfig = {
    url: import.meta.env.VITE_BASE_URL_KEYCLOAK,
    realm: 'artempact',
    clientId: 'artempact-clien-web'
};

const keycloak = new Keycloak(keycloakConfig);

const initOptions = {
    onLoad: 'login-required',
    responseMode: 'fragment',
    flow: 'standard',
    pkceMethod: 'S256',
    redirectUri: import.meta.env.VITE_BASE_URL_FRONTEND,
    checkLoginIframe: true,
};

const KeycloakConfig = { keycloak, initOptions };

export default KeycloakConfig;