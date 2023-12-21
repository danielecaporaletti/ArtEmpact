import Keycloak from 'keycloak-js';
import { BASE_URL_KEYCLOAK } from '../ambient_variable/config-url';
import { BASE_URL_FRONTEND } from '../ambient_variable/config-url';

const keycloakConfig = {
    url: `${BASE_URL_KEYCLOAK}/`,
    realm: 'artempact',
    clientId: 'artempact-clien-web'
};

const keycloak = new Keycloak(keycloakConfig);

const initOptions = {
    onLoad: 'login-required',
    responseMode: 'fragment',
    flow: 'standard',
    pkceMethod: 'S256',
    redirectUri: `${BASE_URL_FRONTEND}/`,
    checkLoginIframe: true,
};

const KeycloakConfig = { keycloak, initOptions };

export default KeycloakConfig;