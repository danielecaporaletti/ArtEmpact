import React, { useState } from "react";
import Logo from "../../assetTemporanei/normal 4.png";
import Pcuadro from "../../assetTemporanei/Pcuadro.png";
import Mickey from "../../assetTemporanei/Mickey.png";
import Lolly from "../../assetTemporanei/Lolly.png";
import Bloody from "../../assetTemporanei/Bloody.png";
import Luke from "../../assetTemporanei/Luke.png";
import WeTog from "../../assetTemporanei/WeTog.png";
import IconOption from "../../icons/IconOption";
import Searchbar from "../../components/JobRequestscomponent/Searchbar";
import CollaborationPage from "../chat/CollaborationPage";
import Button from "../../atoms/Button";
import FooterNavBar from "../../components/FooterNavBar";
import { MenuOption } from "../../components/chatComponents/MenuOption";

const Chat = () => {
  const names = [
    {
      id: 1,
      name: "Pcuadro",
      logo: Pcuadro,
      text: "Salve signor Daniele, saremo interessati ad un'eventuale collaborazione con le...",
    },
    {
      id: 2,
      name: "Mickey_01",
      logo: Mickey,
      text: "Ciao Daniele, vorrei proporti una collaborazione con me e altri artisiti co...",
    },
    {
      id: 3,
      name: "Lolly72$",
      logo: Lolly,
      text: "Ciao Daniele, vorrei proporti una collaborazione con me e altri artisiti co...",
    },
    {
      id: 4,
      name: "Bloody",
      logo: Bloody,
      text: "Salve signor Daniele, saremmo interessati ad un’eventuale collaborazione con le...",
    },
    {
      id: 5,
      name: "Luke52",
      logo: Luke,
      text: "Ciao Daniele, vorrei proporti una collaborazione con me e altri artisiti co...",
    },
    {
      id: 6,
      name: "We-tog srl",
      logo: WeTog,
      text: "Salve signor Daniele, saremmo interessati ad un’eventuale collaborazione con le...",
    },
  ];

  const [typeText, setTypeText] = useState("");
  const [itemButton, setItemButton] = useState("Tutti");
  const [filteredNames, setFilteredNames] = useState(names);
  const [showCollaboration, setShowCollaboration] = useState(false);
  const [showRefute, setShowRefute] = useState(false);
  const [isOpen, setIsOpen] = useState({});

  const toggle = (id) => {
    setIsOpen((prevState) => ({
      ...prevState,
      [id]: !prevState[id],
    }));
  };

  const titles = [
    "Tutti",
    "Aziende",
    "Creativi",
    "Collaborazioni",
    "Rifiutati",
  ];

  const handleSearchInputChange = (event) => {
    setTypeText(event.target.value);
  };

  const handleClick = (title) => {
    setItemButton(title);
    setShowCollaboration(title === "Collaborazioni");

    let filteredItems = names;
    if (title === "Aziende") {
      filteredItems = names.filter(
        (item) =>
          item.name === "Pcuadro" ||
          item.name === "Bloody" ||
          item.name === "We-tog srl"
      );
      setFilteredNames(filteredItems);
      return filteredItems.map((item) => item.name);
    } else if (title === "Creativi") {
      filteredItems = names.filter(
        (item) =>
          item.name === "Mickey_01" ||
          item.name === "Lolly72$" ||
          item.name === "Luke52"
      );
      setFilteredNames(filteredItems);
      return filteredItems.map((item) => item.name);
    } else if (title === "Tutti") {
      setFilteredNames(names);
      return filteredItems.map((item) => item.name);
    } else {
      setFilteredNames([]);
    }
  };

  const filteredBySearch = filteredNames.filter((item) =>
    item.name.toLowerCase().includes(typeText.toLowerCase())
  );

  const handleClickRefute = () => {
    setShowRefute(!showRefute);
  };

  return (
    <div className="min-h-screen bg-white pb-20">
      <div className="pt-2 pb-3 bg-gradient-layout w-full justify-center items-center rounded-b-[1.875rem] overflow-hidden rounded">
        <div className="bg-white min-h-[90vh] w-[95%] flex flex-col rounded-[1.875rem] mx-auto">
          <div className="pt-[1.81rem] flex flex-col justify-center items-center">
            <div className="flex-1 flex justify-center items-center">
              <Searchbar
                placeholder={"Cerca"}
                value={typeText}
                onChange={handleSearchInputChange}
              />
            </div>
            <div
              className={`flex sm:justify-center items-center pt-[1.5rem] gap-[0.70rem] overflow-x-auto whitespace-nowrap pl-[5%] z-10 w-[90%] sm:w-[100%] sm:pl-0 ${
                showRefute ? "blur-[20px]" : ""
              }`}
            >
              {titles.map((title, index) => (
                <ul key={index}>
                  <li>
                    <button
                      className={`border border-[#623BFF] w-[auto] h-[1.9375rem] px-2 
              ${
                itemButton === title
                  ? "bg-[#623BFF] text-white"
                  : "bg-transparent text-[#623BFF]"
              } 
              leading-normal font-montserrat font-[500] text-[#623BFF] text-[0.875rem] rounded-[0.5625rem]`}
                      onClick={() => handleClick(title)}
                      disabled={showRefute}
                    >
                      {title}
                    </button>
                  </li>
                </ul>
              ))}
            </div>
            <div className={`${showRefute ? "blur-[20px]" : ""}`}>
              {showCollaboration && (
                <CollaborationPage onClick={handleClickRefute} />
              )}
            </div>
            <div className="relative flex justify-center items-center w-full">
              <div
                className={`flex flex-col items-center z-10 ${
                  showRefute ? "absolute" : "hidden"
                }`}
              >
                <img
                  src={Logo}
                  alt=""
                  className="w-[3.5rem] h-[4rem] pb-[1rem]"
                />
                <span className="text-center font-montserrat font-[500] text-[1rem]">
                  Diventa un utente Premium per <br />
                  vedere le richieste che hai rifiutato.
                </span>
                <span className="pb-[0.5rem] font-semibold font-montserrat text-[1rem] pt-[1rem]">
                  Ricontattale ed emergi!
                </span>
                <Button type="button" size={"small"}>
                  <span className="font-[500] text-base font-jost">
                    Diventa Premium
                  </span>
                </Button>
                <button
                  className="bg-transparent underline text-[0.75rem] font-[500] pt-[1rem] text-[#2F2F2F]"
                  onClick={handleClickRefute}
                >
                  Torna indietro
                </button>
              </div>
            </div>
            <div className="flex flex-col justify-center items-center overflow-hidden lg:overflow-y-auto">
              <div className="flex-col flex mx-auto sm:w-full screen lg:max-h-[50vh] max-h-[96%]">
                <ul>
                  {filteredBySearch.map((name) => (
                    <li
                      className="w-[94%] pb-8 border-b-[1px] border-[#99B8DD] last:border-0 mx-auto"
                      key={name.id}
                    >
                      <MenuOption
                        isOpen={isOpen}
                        id={name.id}
                        onClick={() => toggle(name.id)}
                      />
                      <div className="bg-white flex flex-col w-[94%] mx-auto">
                        <div className="flex justify-end items-start relative">
                          <div className="flex justify-end items-start absolute pt-[0.14rem]">
                            <button onClick={() => toggle(name.id)}>
                              <IconOption />
                            </button>
                          </div>
                        </div>
                        <div className="flex gap-2 items-end">
                          <img
                            src={name.logo}
                            alt=""
                            className="rounded-full h-[4.75rem] w-[4.75rem]"
                          />
                          <div className="flex flex-col">
                            <span className="text-left text-[1rem] font-montserrat text-[#2F2F2F] font-[500]">
                              {name.name}
                            </span>
                            <span className="text-[0.8125rem] font-[300] text-[#2F2F2F] pt-[0.5rem]">
                              {name.text}
                            </span>
                          </div>
                        </div>
                      </div>
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <style>{`
    @media screen and (max-width: 390px) {
      .screen {
        overflow-x: hidden;
        max-height: 70vh;
        overflow-y: auto;
      }
    }
    `}</style>
      <div className="fixed bottom-[.56rem] left-[50%] translate-x-[-50%]">
        <FooterNavBar activePage={"messaggi"} />
      </div>
    </div>
  );
};

export default Chat;
