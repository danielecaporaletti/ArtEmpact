import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import StepOne from "../../../components/registrationForm/StepOne";
import StepTwo from "../../../components/registrationForm/StepTwo";
import IntroOne from "../../../components/registrationForm/IntroOne";
import Intro from "../../../assetTemporanei/introBusinessOne.jpg";
import IntroTwo from "../../../assetTemporanei/introBusinessTwo.jpg";
import IntroThree from "../../../assetTemporanei/introBusinessThree.png";
import StepForm from "../../../components/registrationForm/StepForm";
import StepIntro from "../../../components/registrationForm/StepIntro";
import { usePostUserFinal } from "../../../hooks/usePostUserFinal";

const data = [
  {
    type: "text",
    value: "stageName",
    label: "Nome",
  },
  {
    type: "text",
    value: "phone",
    label: "Cellulare",
  },
];
const introText = [
  "Scopri ArtEmpact, il tuo nuovo valido aiuto per supportarti nella ricerca della figura creativa più adatta alle esigenze della tua impresa!",
  "Trova il creativo più adatto, fai match e costruisci una collaborazione produttiva per la tua impresa!",
  "l'arte come leva competitiva! Scopri come l'arte può portare valore alla tua aziendanella sezione news di ArtEmpact",
];

const RegistrationCreative = () => {
  const { mutate } = usePostUserFinal();
  const navigate = useNavigate();
  const [page, setPage] = useState(0);
  const [step, setStep] = useState(true);
  const [input, setInput] = useState({
    stageName: "",
    phone: "",
    userType: "business",
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
    if (stageName && phone && city) {
      const data = { city, province, phone, userType, stageName };

      mutate(data);
    } else {
      alert("riempi il form");
    }
    navigate("/home/business/card-creation");
  };
  const registrationStep = [
    <StepOne
      input={input}
      setInput={setInput}
      city={city}
      setCity={setCity}
      setProvince={setProvince}
      isTrue={false}
      data={data}
      page={page}
      setPage={setPage}
      handlerClick={next}
    />,
    <StepTwo page={page} setPage={setPage} handlerClick={next} />,
  ];

  const introStep = [
    <IntroOne
      isTrue={false}
      page={page}
      setPage={setPage}
      text={introText[0]}
      image={Intro}
      handlerClick={nextStep}
      buttonText={"Continua"}
    />,
    <IntroOne
      isTrue={false}
      page={page}
      setPage={setPage}
      text={introText[1]}
      image={IntroTwo}
      handlerClick={nextStep}
      buttonText={"Continua"}
    />,
    <IntroOne
      isTrue={true}
      page={page}
      setPage={setPage}
      text={introText[2]}
      image={IntroThree}
      handlerClick={submit}
      buttonText={"Entra in ArtEmpact"}
    />,
  ];
  return (
    <>
      <section className="bg-[#ebfdff] flex justify-center bg-[url('./assetTemporanei/texture.png')] bg-no-repeat bg-cover min-h-screen ">
        {step ? (
          <StepForm step={registrationStep} page={page} setPage={setPage} />
        ) : (
          <StepIntro step={introStep[page]} />
        )}
      </section>
    </>
  );
};

export default RegistrationCreative;
