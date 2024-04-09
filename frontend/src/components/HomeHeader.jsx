import React, { useState } from "react";
import NotificationCenter from "../components/NotificationCenter";
import IconCircle from "../icons/IconCircle";
import IconBell from "../icons/IconBell";

function HomeHeader() {
  const [isNotificationCenterOpen, setIsNotificationCenterOpen] =
    useState(false);

  const toggleNotificationCenter = () => {
    setIsNotificationCenterOpen(!isNotificationCenterOpen);
  };

  return (
    <>
      <div className="w-full flex flex-row items-center justify-between  mb-[1.75rem] px-[1.56rem]">
        <div className="business__header-icon ">
          <IconCircle color={`var(--color-primary-color)`} />
        </div>
        <div
          className="business__header-icon  cursor-pointer"
          onClick={toggleNotificationCenter}
        >
          <IconBell color={`var(--color-primary-color)`} />
        </div>
      </div>
      {isNotificationCenterOpen && (
        <NotificationCenter
          onClose={() => setIsNotificationCenterOpen(false)}
        />
      )}
    </>
  );
}

export default HomeHeader;
