import React, { useState } from "react";
import HomeCard from "./HomeCard";
import HomeHeader from "../../../components/HomeHeader";
import HomeCTA from "../../../components/HomeCTA";
import FooterNavBar from "../../../components/FooterNavBar";
import SlideSection from "../../../components/SlideSection";
import NotificationCenter from "../../../components/NotificationCenter"; // Aggiunto import
import { useMatchPost } from "../../../hooks/usePostMatch";
import { useFetchNextcard } from "../../../hooks/useNextCard";

function HomeCreative() {
  const maxPage = 3;
  const [count, setCount] = useState(1);
  const [isNotificationCenterOpen, setIsNotificationCenterOpen] = useState(false);

  // Estrai dati con useFetchNextcard
  const [fetchTrigger, setFetchTrigger] = useState(0);
  const { data: nextCardData } = useFetchNextcard(fetchTrigger);
  console.log("nextCardData:", nextCardData);

  // Estrai 'mutate' da useMatchPost e rinominalo in 'postMatchResult'
  const { mutate: postMatchResult } = useMatchPost();

  const toggleNotificationCenter = () => {
    setIsNotificationCenterOpen(!isNotificationCenterOpen);
  };

  const handleSlideSectionChange = (isCompatible) => {
    console.log("handleSlideSectionChange - isCompatible:", isCompatible);
    // Questo log dovrebbe mostrare correttamente true o false.
    postMatchResult({ isCompatible: isCompatible, cardData: nextCardData.data }, {
      onSuccess: () => {
        // Chiamata andata a buon fine, ora puoi ricaricare la prossima carta
        setFetchTrigger(prev => prev + 1);
      }
    });
  };

  return (
    <div className="min-h-screen flex justify-center bg-white items-center">
      <div className="w-[24.375rem] flex flex-col items-center p-y-[.8rem]">
        <HomeHeader onNotificationCenterClick={toggleNotificationCenter} />
        {isNotificationCenterOpen && (
          <NotificationCenter onClose={toggleNotificationCenter} />
        )}
        <HomeCard count={count} setCount={setCount} maxPage={maxPage} data={nextCardData}/>
        <SlideSection count={count} maxPage={maxPage} onSelectionChange={handleSlideSectionChange} />
        <div className="flex-1 flex flex-col justify-between">
          {/* Il componente qui ora occuperà tutto lo spazio disponibile rimanente in verticale */}
          <HomeCTA from={"creative"} />
          <div className="fixed bottom-[.56rem] left-[50%] translate-x-[-50%]">
            <FooterNavBar activePage={"home"} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default HomeCreative;
