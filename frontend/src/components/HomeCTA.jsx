//HomeBusnessCTA.jsx
import { useNavigate } from "react-router-dom";
function HomeCTA({ from }) {
  const navigate = useNavigate();
  let shareProfile = () => navigate("/home/business/profile");
  let visitProfile = () => navigate("/home/business/profile");
  if (from === "business") {
    shareProfile = () => navigate("/home/creative/profile"); // share link da fare
    visitProfile = () => navigate("/home/creative/profile"); // da aggiungere id /:id e se creative o business
  }
  return (
    <div className="flex  h-[3.3125rem] w-full gap-[0.75rem] justify-center">
      <button onClick={shareProfile} className="primary-btn w-[9.6875rem] ">
        <span className="text-white font-medium text-[1rem] font-['Jost']">
          Condividi il profilo
        </span>
      </button>
      <button onClick={visitProfile} className="secondary-btn w-[7.625rem]">
        <span className="text-primary-color font-medium text-[1rem] font-['Jost']">
          Visita il profilo
        </span>
      </button>
    </div>
  );
}
export default HomeCTA;
