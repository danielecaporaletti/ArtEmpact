import IconX from "../../icons/IconX";
import myGif from "../../../src/gif/match.gif";
import { useNavigate, useLocation } from "react-router-dom";

function MatchPage() {
  const navigate = useNavigate();
  const location = useLocation();
  const currentPath = location.pathname;

  const getHomePath = () => {
    switch (currentPath) {
      case "/home/business":
        return "/home/business";
      case "/home/creative":
        return "/home/creative";
      default:
        // Handle unexpected paths (optional)
        return "/"; // Redirect to default home if path not found
    }
  };

  const navigateToHome = () => navigate(getHomePath());

  return (
    <div className="w-full min-h-screen flex flex-col bg-white items-center">
      <div className="w-[0.75rem] h-[1rem] my-[0.62rem] mx-[1.56rem] text-primary-color self-start">
        <IconX></IconX>
      </div>
      <div className="h-[19.625rem] aspect-square">
        <img src={myGif} alt="Match animations" />
      </div>
      <div className="text-[1.875rem] text-sans text-center text-primary-color font-bold mb-[1.25rem]">
        <p>
          <span className="uppercase">è</span> un match!
        </p>
      </div>
      <div className="text-[1rem] text-center text-black font-light w-[15.875rem] mb-[3.12rem]">
        <p className="break-word">
          Complimenti hai fatto un match. La tua avventura inizia proprio ora!
        </p>
      </div>
      <div className="primary-btn w-[11.0625rem] min-h-[3.3125rem] text-[1rem] text-white mb-[0.56rem]">
        <p className="">Scrivi un messaggio</p>
      </div>
      <div className="secondary-btn w-[7.625rem] min-h-[3.3125rem] text-[1rem] mb-[4.69rem] font-medium">
        <p>Visita il profilo</p>
      </div>
      <div className="text-[1rem] text-black font-medium pb-[4.5rem]" onClick={navigateToHome}>
        <p>Fai altri match</p>
      </div>
    </div>
  );
}

export default MatchPage;
