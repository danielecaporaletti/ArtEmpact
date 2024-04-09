import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import StepOne from "../../../components/registrationForm/StepOne";
import StepTwo from "../../../components/registrationForm/StepTwo";
import IntroOne from "../../../components/registrationForm/IntroOne";
import Intro from "../../../assetTemporanei/IntroOneMobile.jpg";
import IntroTwo from "../../../assetTemporanei/introTwoMobile.jpg";
import IntroThree from "../../../assetTemporanei/introThreeMobile.jpg";
import StepForm from "../../../components/registrationForm/StepForm";
import StepIntro from "../../../components/registrationForm/StepIntro";
import { introText, subText } from "../../../data/RegistrationFormData";
import { usePostUserFinal } from "../../../hooks/usePostUserFinal";

const data = [
  {
    type: "tel",
    value: "phone",
    label: "Cellulare",
  },
];

const RegistrationCreative = () => {
  const { mutate } = usePostUserFinal();
  const navigate = useNavigate();
  const [page, setPage] = useState(0);
  const [step, setStep] = useState(true);
  const [input, setInput] = useState({
    stageName: "empty",
    phone: "",
    userType: "creative",
  });
  const [city, setCity] = useState("");
  const [province, setProvince] = useState("");
  const nextStep = () => setPage(page + 1);

  const next = () => {
    setPage(0);
    setStep(false);
  };

  const submit = (e) => {
    e.preventDefault();
    const { stageName, phone, userType } = input;
    if (phone && city) {
      const data = { city, province, phone, userType, stageName };

      mutate(data);
    } else {
      alert("riempi il form");
    }
    navigate("/home/creative/card-creation");
  };
  const registrationStep = [
    <StepOne
      input={input}
      setInput={setInput}
      city={city}
      setCity={setCity}
      setProvince={setProvince}
      isTrue={true}
      data={data}
      page={page}
      setPage={setPage}
    />,
    <StepTwo page={page} setPage={setPage} handlerClick={next} />,
  ];

  const introStep = [
    <IntroOne
      isTrue={false}
      page={page}
      setPage={setPage}
      text={introText[0]}
      intro={subText[0]}
      image={Intro}
      handlerClick={nextStep}
      buttonText={"Continua"}
    />,
    <IntroOne
      isTrue={false}
      page={page}
      setPage={setPage}
      text={introText[1]}
      intro={subText[1]}
      image={IntroTwo}
      handlerClick={nextStep}
      buttonText={"Continua"}
    />,
    <IntroOne
      isTrue={false}
      page={page}
      setPage={setPage}
      text={introText[2]}
      intro={subText[2]}
      image={IntroThree}
      handlerClick={submit}
      buttonText={"Entra in ArtEmpact"}
    />,
  ];

  return (
    <>
      <div className="bg-[#ebfdff] flex justify-center bg-[url('./assetTemporanei/texture.png')] bg-no-repeat bg-cover min-h-screen">
        {step ? (
          <StepForm step={registrationStep} page={page} setPage={setPage} />
        ) : (
          <StepIntro step={introStep[page]} />
        )}
      </div>
    </>
  );
};

export default RegistrationCreative;
