import React from 'react';
import NoSvg from "./no.svg";

// Se vuoi comunicare al componente padre, passa la funzione handleClick come prop
function No({ onClick }) {
  
  // Gestisce l'evento click sul componente
  const handleClick = () => {
    console.log(false);
    onClick(false);
  };

  return <img src={NoSvg} alt="Your SVG" onClick={handleClick} />;
}

export default No;
