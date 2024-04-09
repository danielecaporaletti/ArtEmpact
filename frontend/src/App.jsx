//App.jsx

import React from "react";
import KeycloakConfig from "./config/keycloak-config";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

import GlobalStyles from "./styles/GlobalStyles";
import Registration from "./pages/registration/Registration";
import RegistrationBusiness from "./pages/registration/registrationBusiness/RegistrationBusiness";
import RegistrationCreative from "./pages/registration/registrationCreative/RegistrationCreative";
import HomeBusiness from "./pages/home/homeBusiness/HomeBusiness";
import HomeCreative from "./pages/home/homeCreative/HomeCreative";
import ProfileBusiness from "./pages/profiles/profileBusiness/ProfileBusiness";
import ProfileCreative from "./pages/profiles/profileCreative/ProfileCreative";
import JobrequestFromBusinessToCreative from "./pages/requestsForOpportunities/cardRequestBusinessProfile/jobRequestFromBusinessToCreative/JobRequestFromBusinessToCreative";
import JobrequestFromCreativeToBusiness from "./pages/requestsForOpportunities/cardRequestCreativeProfile/JobrequestFromCreativeToBusiness/JobrequestFromCreativeToBusiness";
import CreativeSeekCollaborationWithOtherCreative from "./pages/requestsForOpportunities/cardRequestCreativeProfile/creativeSeekCollaborationWithOtherCreative/CreativeSeekCollaborationWithOtherCreative";
import CreationBusinessCards from "./pages/requestsForOpportunities/cardRequestBusinessProfile/CreationBusinessCards";
import CreationCreativeCards from "./pages/requestsForOpportunities/cardRequestCreativeProfile/CreationCreativeCards";
import News from "./pages/news/News";
import Chat from "./pages/chat/Chat";

import MatchPage from "./pages/home/MatchPage";
import NewProject from "./pages/profiles/profileCreative/creativeProjects/NewProject";
import ProjectView from "./pages/profiles/profileCreative/creativeProjects/ProjectView";
import NearbyArtists from "./pages/profiles/profileCreative/NearbyArtists";
import ArtistsPreview from "./pages/profiles/profileCreative/ArtistsPreview";
import BusinessPcreativeV from "./pages/profiles/profileBusiness/BusinessPcreativeV";
import BusinessAI from "./pages/profiles/profileBusiness/BusinessAI";
import BusinessAI2 from "./pages/profiles/profileBusiness/BusinessAI2";
import RedirectProcessPostLogin from "./pages_routes/routes_logic_components/RedirectProcessPostLogin";

/**
 * This components inits routing, global styles and the query client
 * @returns {JSX.Element} The App component
 */
export default function App() {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        refetchOnMount: false,
        // refetchOnReconnect: false,
        // refetchOnWindowFocus: false,
      },
    },
  });
  return (
    <>
      <ReactKeycloakProvider
        authClient={KeycloakConfig.keycloak}
        initOptions={KeycloakConfig.initOptions}
      >
        <GlobalStyles />

        <QueryClientProvider client={queryClient}>
          <ReactQueryDevtools initialIsOpen={false} />

          <BrowserRouter>
            <Routes>
              {/* <Route path='/' element={<RedirectProcessPostLogin />} /> */}
              <Route path="/" element={<RedirectProcessPostLogin />} />
              <Route path="/registration/" element={<Registration />} />

              <Route
                path="/registration/business"
                element={<RegistrationBusiness />}
              />
              <Route
                path="/registration/creative"
                element={<RegistrationCreative />}
              />

              <Route path="/home/business" element={<HomeBusiness />} />
              <Route path="/home/creative" element={<HomeCreative />} />

              <Route
                path="/home/business/profile"
                element={<ProfileBusiness />}
              />
              <Route
                path="/home/creative/profile"
                element={<ProfileCreative />}
              />
              <Route
                path="/home/creative/profile-preview/:id"
                element={<ArtistsPreview />}
              />
              <Route
                path="/home/creative/profile/nearby-artists"
                element={<NearbyArtists />}
              />
              <Route
                path="/home/creative/profile/new-project"
                element={<NewProject />}
              />
              <Route
                path="home/creative/profile/project-view"
                element={<ProjectView />}
              />

              <Route
                path="/home/business/card-creation"
                element={<CreationBusinessCards />}
              />
              <Route
                path="/home/business/job-request-from-business-to-creative"
                element={<JobrequestFromBusinessToCreative />}
              />

              <Route
                path="/home/creative/card-creation"
                element={<CreationCreativeCards />}
              />
              <Route
                path="/home/creative/job-request-from-creative-to-business"
                element={<JobrequestFromCreativeToBusiness />}
              />
              <Route
                path="/home/creative/creative-seeking-collaboration-with-other-creative"
                element={<CreativeSeekCollaborationWithOtherCreative />}
              />
              <Route path="/home/news" element={<News />} />
              <Route path="/home/chat" element={<Chat />} />

              {/* Routes provvisiorie */}
              <Route path="/home/match-test" element={<MatchPage />} />
              {/* <Route path="/home/EditInfo" element={<EditInfo />} /> */}
              {/* <Route path="/home/ProfileSettings" element={<Settings />} /> */}
              <Route
                path="/home/business-profile-creative-view"
                element={<BusinessPcreativeV />}
              />
              <Route
                path="/home/new-ai-collaborations"
                element={<BusinessAI />}
              />
              <Route
                path="/home/new-ai-collaborations-2"
                element={<BusinessAI2 />}
              />
            </Routes>
          </BrowserRouter>
        </QueryClientProvider>
      </ReactKeycloakProvider>
    </>
  );
}
