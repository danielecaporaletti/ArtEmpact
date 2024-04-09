import React, { useState } from "react";
import HomeCard from "./HomeCard";
import HomeHeader from "../../../components/HomeHeader";
import HomeCTA from "../../../components/HomeCTA";
import FooterNavBar from "../../../components/FooterNavBar";
import SlideSection from "../../../components/SlideSection";
import NotificationCenter from "../../../components/NotificationCenter"; // Aggiunto import

function HomeCreative() {
  const maxPage = 3;
  const [count, setCount] = useState(1);
  const [isNotificationCenterOpen, setIsNotificationCenterOpen] =
    useState(false);

  const toggleNotificationCenter = () => {
    setIsNotificationCenterOpen(!isNotificationCenterOpen);
  };

  return (
    <div className="min-h-screen flex justify-center bg-white items-center">
      <div className="w-[24.375rem] flex flex-col items-center p-y-[.8rem]">
        <HomeHeader onNotificationCenterClick={toggleNotificationCenter} />
        {isNotificationCenterOpen && (
          <NotificationCenter onClose={toggleNotificationCenter} />
        )}
        <HomeCard count={count} setCount={setCount} maxPage={maxPage} />
        <SlideSection count={count} maxPage={maxPage} />
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
