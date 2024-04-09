import React, { useState } from "react";
import LogoAE from "../icons/LogoAE";
import IconBell from "../icons/IconBell";

function ProfileHeaderIconBell() {
  const [isNotificationCenterOpen, setIsNotificationCenterOpen] =
    useState(false);

  const toggleNotificationCenter = () => {
    setIsNotificationCenterOpen(!isNotificationCenterOpen);
  };
  return (
    <div className="flex justify-between items-center">
      <div className="w-[8rem]">
        <LogoAE />
      </div>
      <div
        className="business__header-icon cursor-pointer"
        onClick={toggleNotificationCenter}
      >
        <IconBell color={`var(--color-primary-color)`} />
      </div>
    </div>
  );
}
export default ProfileHeaderIconBell;
