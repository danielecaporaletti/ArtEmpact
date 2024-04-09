import React, { useState, useEffect } from "react";
import { Navigate } from "react-router-dom";
import { useKeycloak } from "@react-keycloak/web";
import logo from "../../assets/logo/logo-long.svg";

const RedirectProcessPostLogin = () => {
  const { keycloak, initialized } = useKeycloak();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (initialized) {
      keycloak
        .loadUserProfile()
        .then(() => setLoading(false))
        .catch((err) => {
          setLoading(false);
        });
    }
  }, [keycloak, initialized]);

  if (loading) {
    return (
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "100vh",
          width: "100vw",
        }}
      >
        <img src={logo} alt="logo loading ArtEmpact" />
      </div>
    );
  }

  if (keycloak.hasRealmRole("business")) {
    return <Navigate to="/home/business" />;
  }

  if (keycloak.hasRealmRole("creative")) {
    return <Navigate to="/home/creative" />;
    // return (
    //   <Navigate to="/home/creative/job-request-from-creative-to-business" />
    // );
  }
  // If user has none of the roles, redirect to /register
  return <Navigate to="/registration" />;
};

export default RedirectProcessPostLogin;
