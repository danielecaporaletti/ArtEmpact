REMEMBER THAT CLIENT SECRET AND PASSWORD ARE NOT SAVED IN JSON IF YOU MAKE AN EXPORT FROM KEYCLOAK.
Find the position in json, search key "secret" and change it.
Find the position in json, search key "smtpServer" and change it.

FROM KEYCLOAK 18 IT IS NO LONGER POSSIBLE TO FREE IMPORT REALMS FROM A JSON FILE. To do this you need to open the json file and delete the entire "authorizationSettingsnode" node.