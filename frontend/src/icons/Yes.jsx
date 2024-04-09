import YesSvg from "./yes.svg";

function Yes({ onClick }) {

    // Gestisce l'evento click sul componente
    const handleClick = () => {
      console.log(true);
      onClick(true);
    };

  return <img src={YesSvg} alt="Your SVG" onClick={handleClick}/>;
}

export default Yes;
