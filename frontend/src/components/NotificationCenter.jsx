// NotificationCenter.jsx
import React, { useEffect, useState } from "react";
import IconMailClosed from "../icons/IconMailClosed";
import IconMailOpened from "../icons/IconMailOpened";
import IconX from "../icons/IconX";
import { GoArrowRight } from "react-icons/go";

function NotificationCenter({ onClose }) {
  const [isExiting, setIsExiting] = useState(false);

  useEffect(() => {
    const notificationCenter = document.getElementById("notification-center");

    if (notificationCenter) {
      notificationCenter.classList.remove("exiting");

      notificationCenter.animate(
        [
          { transform: "translateX(100%)", opacity: 0 },
          { transform: "translateX(0)", opacity: 1 },
        ],
        {
          duration: 500,
          easing: "ease-out",
          fill: "forwards",
        }
      );
    }
  }, []);

  const handleClose = () => {
    const notificationCenter = document.getElementById("notification-center");

    if (notificationCenter) {
      setIsExiting(true);

      notificationCenter.animate(
        [
          { transform: "translateX(0)", opacity: 1 },
          { transform: "translateX(100%)", opacity: 0 },
        ],
        {
          duration: 300,
          easing: "ease-in",
          fill: "forwards",
        }
      );
    }

    setTimeout(() => {
      onClose();
    }, 300);
  };

  const messages = [
    { typeMessage: "match", from: "Puli srl", readed: "false" },
    { typeMessage: "collaboration-request", from: "unknow", readed: "true" },
    { typeMessage: "message", from: "Loly72", readed: "true" },
  ];

  return (
    <div
      id="notification-center"
      className={`flex flex-col justify-between pt-[2.5rem] fixed right-0 top-0 h-screen w-[16.6878rem] bg-white shadow-btn-rounded p-4 z-50 rounded-l-[2rem] ${
        isExiting ? "exiting" : ""
      }`}
    >
      <div className="">
        <div id="notification-center" className="flex flex-col">
          <div className="flex justify-between items-center w-[13.375rem] h-[3.56345] mb-[1.41rem] mt-[2.65rem]">
            <div
              className="w-[1.5rem] h-[1.7rem] cursor-pointer"
              onClick={handleClose}
            >
              <IconX className="" />
            </div>
            <div className="">
              <p className="text-pen-color text-lg font-medium">
                Centro Notifiche
              </p>
            </div>
          </div>
          <div className="text-gray-700 ml-[.59rem]">
            {messages.map((message, index) => (
              <div
                key={index}
                className="mb-4 flex items-center gap-[1.19rem]"
                style={{
                  marginBottom: index === messages.length - 1 ? 0 : "1.94rem",
                }}
              >
                <div className="">
                  {message.readed === "true" ? (
                    <IconMailOpened style={{ color: "#2F2F2F" }} />
                  ) : (
                    <IconMailClosed style={{ color: "#623BFF" }} />
                  )}
                </div>
                <div className="flex">
                  <p
                    className={`text-${
                      message.readed === "true" ? "pen-black" : "primary-color"
                    } text-[0.875rem] text-start font-normal leading-normal`}
                  >{`${getMessageContent(message)}`}</p>
                  <div className="line" />
                </div>
              </div>
            ))}
          </div>
          <div className="mt-[1.75rem] ml-[.63rem] text-[1.125rem] text-primary-color font-medium">
            Badge
          </div>
          <div className="text-[0.75rem] ml-[.63rem] mr-[1.5rem] mb-[1.19rem]">
            <p>
              Scopri tutti i badge che hai raccolto e scegli quale mostrare al
              tuo pubblico{" "}
            </p>
          </div>
          <div className="">
            <div className="text-[.75rem] ml-[.63rem] mb-[1.63rem] flex justify-between">
              <p className="w-[9.0652rem]">
                Badge: Hai completato il tuo profilo
              </p>
              <div className="h-7 w-7 bg-primary-color rounded-full flex justify-center">
                <GoArrowRight
                  size={"65%"}
                  style={{
                    color: "#fff",
                    alignSelf: "center",
                  }}
                />
              </div>
            </div>
          </div>
          <div className="">
            <div className=" text-[.75rem] ml-[.63rem] flex justify-between">
              <p className="w-[9.0652rem]">Badge: Hai fatto 10 match!</p>
              <div className="h-7 w-7 bg-primary-color rounded-full flex justify-center">
                <GoArrowRight
                  size={"65%"}
                  style={{
                    color: "#fff",
                    alignSelf: "center",
                  }}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="text-[1rem] text-primary-color text-center underline font-roboto font-light mb-[2.62rem]">
        <p>Segna tutte come già lette</p>
      </div>
    </div>
  );

  function getMessageContent(message) {
    switch (message.typeMessage) {
      case "match":
        return `Hai fatto match con ${message.from}`;
      case "collaboration-request":
        return `Hai una nuova richiesta di collaborazione`;
      case "message":
        return `Hai un nuovo messaggio da ${message.from}`;
      default:
        return "";
    }
  }
}

export default NotificationCenter;
