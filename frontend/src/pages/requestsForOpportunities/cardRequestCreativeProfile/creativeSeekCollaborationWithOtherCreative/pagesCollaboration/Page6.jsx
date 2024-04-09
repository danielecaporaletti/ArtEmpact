import { useNavigate } from "react-router-dom";
import gif from "../../../../../gif/sendpost.gif";
import gif2 from "../../../../../gif/error.gif";

const Page6 = () => {
  const navigate = useNavigate();

  const navigation = () => {
    navigate("/home/creative");
  };
  
  return (
      <div className="flex flex-col justify-start items-center h-screen">
        <>
        <img
          src={gif}
          className="lg:w-[10%] w-[40%] h-auto pt-[10rem]"
          alt="gif"
        />
        <div className="flex flex-col pt-[2rem] max-[375max]:pt-[0rem]">
          <span className="text-center">✅</span>
          <span className="text-white text-center text-[1rem] font-['Montserrat'] font-[600]">
            La tua richiesta è stata <br />
            pubblicata!
          </span>
        </div>
        <div className="pt-[6rem] max-[375px]:pt-[5rem]">
          <button
            className="rounded-[14px] w-[229px] h-[53px] sm:hover:bg-light-violet bg-primary-color sm:active:bg-primary-color text-white text-[16px] font-['Jost']"
            onClick={() => navigation()}
          >
            Vai alla Home
          </button>
        </div>
        </>
     
        {/* <>
        <img
          src={gif2}
          className="lg:w-[10%] w-[40%] h-auto pt-[10rem]"
          alt="gif"
        />
        <div className="flex flex-col pt-[2rem] max-[375max]:pt-[0rem]">
          <span className="text-white text-center text-[1rem] font-['Montserrat'] font-[600]">
            Ci dispiace la tua richiesta <br />
            presenta un errore
          </span>
          <span className="text-white text-center text-[1rem] font-['Montserrat'] font-[600]">
            {" "}<br />
          </span>
        </div>
        <div className="pt-[6rem] max-[375px]:pt-[5rem] flex">
          <button
            className="rounded-[14px] w-[229px] h-[53px] sm:hover:bg-light-violet bg-primary-color sm:active:bg-primary-color text-white text-[16px] font-['Jost']"
            onClick={() => navigation()}
          >
            Indietro
          </button>
          <button
            className="rounded-[14px] w-[229px] h-[53px] sm:hover:bg-primary-color sm:hover:text-white sm:active:bg-primary-color text-primary-color text-[16px] font-['Jost']"
            onClick={() => navigation()}
          >
            Contattaci
          </button>
        </div>
        </> */}
       
       <div className="absolute bottom-0 pb-4">
          <p className="text-center text-[1rem] max-[280px]:text-[0.8rem] font-semibold font-['Montserrat'] text-primary-color">
            Aggiorna il tuo profilo per fare match
          </p>
        </div>
      </div>
   
  );
};

export default Page6;
