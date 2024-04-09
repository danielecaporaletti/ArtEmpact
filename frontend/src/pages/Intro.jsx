import React from "react";
import { Link } from "react-router-dom";

const linkStyle = {
  color: "blue",
  textDecoration: "none",
  margin: "0 10px",
  fontWeight: "bold",
  fontSize: "24px",
};

const divStyle = {
  padding: "20px",
  backgroundColor: "#f2f2f2",
  borderRadius: "10px",
  boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
  textAlign: "center",
  fontSize: "24px",
  maxWidth: "50%",
};

const containerStyle = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  height: "100vh",
};

const Intro = () => {
  return (
    <div style={containerStyle}>
      <div style={divStyle}>
        <p>
          Vai al percorso
          <Link to="/registration" style={linkStyle}>
            /registration
          </Link>
          per registrarti, oppure accedi e vai direttamente nella home
          <Link to="/home/business" style={linkStyle}>
            /home/business
          </Link>
          <Link to="/home/creative" style={linkStyle}>
            /home/creative
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Intro;
