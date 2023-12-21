# Struttura del progetto

/frontend-artempact
├── node_modules/       //Pacchetti del progetto installati.   
├── public/             //File accessibili al pubblico
│   ├── index.html
│   ├── favicon.ico
├── src/               // tutto il codice sorgente dell'applicazione
│   ├── assets/        // per immagini, stili globali, ecc.
│   ├── components/    // componenti React riutilizzabili
|   ├── config/        // configurazione dell'ambiente
│   ├── services/      // funzioni di utilità, servizi API, ecc.
│   ├── hooks/         // custom hooks
│   ├── locales/       // per la localizzazione/internazionalizzazione
│   ├── routes/        // gestione delle rotte
│   ├── store/         // se stai utilizzando Redux o MobX, ecc.
│   ├── utils/         // utility functions
│   ├── views/         // componenti a livello di pagina
│   │   ├── Home/
│   │   │   ├── components/
│   │   │   ├── tests/
│   │   │   ├── Home.js
│   │   ├── Profile/
│   │   │   ├── components/
│   │   │   ├── tests/
│   │   │   ├── Profile.js
|   |   ├── test/              // test globali (unitari, integrazione, end-to-end)
|   |   ├── scripts/           // script personalizzati
│   ├── App.js
│   ├── App.css
│   ├── index.js
│   ├── index.css
│   ├── reportWebVitals.js
├── package.json
├── package-lock.json
├── .gitignore
├── README.md


1. `node_modules/`: Questa cartella contiene tutti i pacchetti del progetto che vengono installati attraverso npm o yarn.
2. `public/`: Questa cartella contiene file che saranno accessibili al pubblico, come l'HTML principale (`index.html`) e le icone dell'applicazione (`favicon.ico`).
3. `src/`: La cartella `src` è il cuore dell'applicazione React. Contiene tutto il codice sorgente dell'applicazione.
- - `assets/`: Questa cartella è per immagini, stili globali e altri file di risorse statiche.
- - `components/`: I componenti riutilizzabili vengono messi in questa cartella. Questi componenti sono principalmente componenti di presentazione e dovrebbero essere il più indipendenti possibile.
- - `containers/`: Questa cartella contiene componenti che connettono più componenti e gestiscono la logica di business.
- - `services/`: Questa cartella contiene funzioni di utilità, servizi API e altro codice che si interfaccia con i servizi esterni.
- - `hooks/`: Questa cartella è per i custom hooks di React. I custom hooks permettono di estrarre la logica dei componenti in funzioni riutilizzabili.
- - `store/`: Se stai utilizzando una libreria di gestione dello stato come Redux o MobX, il codice relativo allo store viene messo in questa cartella.
- - `routes/`: Questa cartella è per la gestione delle rotte dell'applicazione.
- - `utils/`: Questa cartella contiene funzioni di utilità che possono essere utilizzate in tutto l'applicazione.
- - `locales/`: Questa cartella è per la localizzazione/internazionalizzazione. Puoi mantenere qui i file di traduzione e altro codice relativo alla localizzazione.
- - `views/`: Questa cartella contiene i componenti a livello di pagina. Ogni sottocartella rappresenta una vista o una pagina separata dell'applicazione.
4. `test/`: Questa cartella è per i test globali, come i test unitari, di integrazione e end-to-end.
5. `config/`: Questa cartella contiene i file di configurazione dell'ambiente.
6. `scripts/`: Questa cartella contiene gli script personalizzati che possono essere utilizzati per automatizzare compiti ripetitivi.
7. `package.json`: Questo file contiene metadati sull'applicazione e l'elenco delle sue dipendenze.
8. `.gitignore`: Questo file dice a Git quali file o cartelle non tracciare.
9. `Dockerfile`: Questo file definisce come Docker deve costruire un'immagine Docker per l'applicazione.
10. `.dockerignore`: Questo file indica a Docker quali file o cartelle ignorare quando costruisce l'immagine.

# Dipendenze installate in React

- axios 1.4.0
- keycloak-js 21.1.1
- @react-keycloak/web 3.4.0
- react-router-dom 6.11.2
- redux 4.2.1 ( da installare )

# Moduli installati in Spring Boot