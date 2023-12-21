import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import KeycloakConfig from './config/keycloak-config';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import RegistrationFlow from './pages_routes/registerFlowPages/RegistrationFlow';
import BusinessFlowPages from './pages_routes/businessFlowPages/BusinessFlowPages';
import CreativeFlowPages from './pages_routes/creativeFlowPages/CreativeFlowPages';
import RedirectProcessPostLogin from './pages_routes/routes_logic_components/RedirectProcessPostLogin';

function App() {
    return (
      <div className="App">
        <ReactKeycloakProvider authClient={KeycloakConfig.keycloak} initOptions={KeycloakConfig.initOptions}>
          <Router>
            <Routes>
              <Route path='/' element={<RedirectProcessPostLogin />} />

              <Route path='/registration' element={<RegistrationFlow />} />
              
              <Route path='/business' element={
                <Routes>
                    <Route path='/' element={<BusinessFlowPages />} />
                </Routes>} 
              />
              <Route path='/creative' element={
                <Routes>
                    <Route path='/' element={<CreativeFlowPages />} />
                </Routes>} 
              />
            </Routes>
          </Router>
        </ReactKeycloakProvider>
      </div>
    );
  }
  
  export default App;
