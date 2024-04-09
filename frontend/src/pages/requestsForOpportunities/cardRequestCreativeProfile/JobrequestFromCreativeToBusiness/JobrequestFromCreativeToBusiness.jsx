import React from "react";
import { useNavigate } from "react-router";
import NavigationRequests from "../../../../components/JobRequestscomponent/NavigationRequests";
import { useState } from "react";
import Page1 from "./pagesCreative/Page1";
import Page2 from "./pagesCreative/Page2";
import Page3 from "./pagesCreative/Page3";
import Page4 from "./pagesCreative/Page4";
import Page5 from "./pagesCreative/Page5";
import Page6 from "./pagesCreative/Page6";
import Page7 from "./pagesCreative/Page7";
import { BackPage } from "../../../../components/JobRequestscomponent/BackPage";
import PagesPopUp from "../../../../components/JobRequestscomponent/PagesPopUp";
import { QueryProvider } from "../../../../contexts/CreateCardContext";

const JobrequestFromCreativeToBusiness = () => {
  const names = [
    {
      id: 2,
      title: "Luogo",
    },
    {
      id: 3,
      title: "Skills",
    },
    {
      id: 4,
      title: "Job Description",
    },
    {
      id: 5,
      title: "Design",
    },
  ];
  const [pages, setPages] = useState(0);
  const [hover, setHover] = useState(false);
  const navigateTo = useNavigate();
  const [show, setShow] = useState(false);

  const toggleDropdown = () => {
    setHover(hover => !hover);
  };
  const updateShow = () => {
    setShow(true);
  };

  const updateHidden = () => {
    setShow(false);
  };

  const changePage = () => {
    setPages(pages + 1);
  };

  const backPage = () => {
    if (pages > 0) {
      setPages(pages - 1);
    }

    if (
      pages === 0) {
        navigateTo("/home/creative/card-creation");
      }
  };

  return (
    <QueryProvider>
      <div className="flex flex-col justify-center items-center md:overflow-y-hidden xl:overflow-y-auto lg:overflow-y-auto overflow-x-hidden">
        <div className="bg-gradient-layout w-full h-auto">
          <BackPage onClick={backPage} hidden={pages === 6} />
          <p
            className="text-primary-color font-[500] text-center text-[16px] leading-normal font-['Jost'] pt-10"
            hidden={pages === 6}
          >
            Sto cercando un lavoro
          </p>
          <NavigationRequests
            pages={pages}
            names={names}
            hidden={pages === 6}
            hover={hover}
            toggleDropdown={toggleDropdown}
            setPages={setPages}
            show={updateShow}
            update={show}
          />
          <div>
            {pages === 0 && <Page1 pages={pages} changePage={changePage} />}
            {pages === 1 && !show && (
              <Page2 pages={pages} changePage={changePage} show={show} />
            )}
            {pages === 2 && <Page3 pages={pages} changePage={changePage} />}
            {pages === 3 && <Page4 pages={pages} changePage={changePage} />}
            {pages === 4 && <Page5 pages={pages} changePage={changePage} />}
            {pages === 5 && <Page6 pages={pages} changePage={changePage} />}
            {pages === 6 && <Page7 pages={pages} changePage={changePage} />}
          </div>
        </div>
        {hover && (
          <>
            <div
              className="fixed top-0 left-0
      w-full h-full bg-black opacity-50 sm:bg-transparent sm:opacity-0"
              onClick={toggleDropdown}
            ></div>
            <div
              className="z-10 sm:ml-[30%] flex justify-center items-center
      transition-transform duration-300 top-[90%] w-[100%] max-[280px]:top-[85%]
      transform -translate-y-full fixed hover:translate-y-0
      sm:top-[20%] sm:transition-none sm:hover:translate-y-0 lg:top-[35%] xl:top-[28%] 2xl:top-[20%]"
            >
              <PagesPopUp show={show} update={updateHidden} />
            </div>
          </>
        )}
      </div>
    </QueryProvider>
  );
};

export default JobrequestFromCreativeToBusiness;
