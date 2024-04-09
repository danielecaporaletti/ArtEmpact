import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import logo from "../assetTemporanei/logo_iscrizione.png";
import CardLink from "../atoms/CardLink";
import FooterNavBar from "./FooterNavBar";
import Button from "../atoms/Button";

const BusinessCardRegistration = ({ data }) => {
  const navigate = useNavigate();
  const [navigationDestination, setNavigationDestination] = useState(null);
  const nextPageJob = () =>
    navigate("/home/business/job-request-from-business-to-creative");

  return (
    <div className="flex flex-col items-center justify-around px-4 w-full md:px-32">
      <div className="w-[136px] mt-14 md:mt-8 mb-4 md:self-start">
        <Link to="/">
          <img src={logo} alt="logo" />
        </Link>
      </div>
      <div className="text-center text-[30px] text-[#623BFF] font-bold font-sans leading-8">
        <p className="md:hidden">
          Sono qui per
          <br />
          trovare:
        </p>
        <p className="max-sm:hidden">Sono qui per trovare:</p>
      </div>

      <CardLink
        index={navigationDestination}
        setIndex={setNavigationDestination}
        data={data}
      />
      <div className="w-[229px]">
        <Button
          type="button"
          disabled={navigationDestination === null ? true : false}
          handleClick={nextPageJob}
        >
          continua
        </Button>
      </div>

      <p className="max-sm:hidden text-sm font-bold font ">
        Modifica le informazioni nel tuo profilo per fare match facilmente!
      </p>
      <Link className="text-sm font-bold md:hidden mb-12" to="/home/creative">
        Vai alla Home
      </Link>

      <div className="fixed w-full bottom-2 left-1/2 -translate-x-1/2 max-w-md px-4 md:hidden">
        <FooterNavBar activePage={"cerca"} />
      </div>
    </div>
  );
};

export default BusinessCardRegistration;
