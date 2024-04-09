import NavigationRequests from "../../../../components/JobRequestscomponent/NavigationRequests";
import { useState } from "react";
import Page1 from "./pagesRequest/Page1";
import Page2 from "./pagesRequest/Page2";
import Page3 from "./pagesRequest/Page3";
import Page4 from "./pagesRequest/Page4";
import Page5 from "./pagesRequest/Page5";
import Page6 from "./pagesRequest/Page6";
import { BackPage } from "../../../../components/JobRequestscomponent/BackPage";
import PagesPopUp from "../../../../components/JobRequestscomponent/PagesPopUp";
import { useNavigate } from "react-router-dom";
import { QueryProvider } from "../../../../contexts/CreateCardContext";

const JobrequestFromBusinessToCreative = () => {
  const [pages, setPages] = useState(0);
  const [hover, setHover] = useState(false);
  const [show, setShow] = useState(false);
  const navigate = useNavigate();

  const names = [
    {
      id: 2,
      title: "Chi siamo",
    },
    {
      id: 3,
      title: "Il progetto",
    },
    {
      id: 4,
      title: "Design",
    },
  ];

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
    } else {
      navigate("/home/business/card-creation");
    }
  };
  return (
    <QueryProvider>
      <div className="flex flex-col justify-center items-center md:overflow-y-hidden xl:overflow-y-auto lg:overflow-y-auto">
        <div className="bg-gradient-layout w-full h-auto">
          <BackPage onClick={backPage} hidden={pages === 5} />
          <p
            className="text-primary-color font-[500] text-center text-[16px] leading-normal font-['Jost'] pt-10"
            hidden={pages === 5}
          >
            Sto cercando un creativo
          </p>
          <NavigationRequests
            pages={pages}
            names={names}
            hidden={pages === 5}
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
      sm:top-[20%] sm:transition-none sm:hover:translate-y-0 lg:top-[35%] xl:top-[25%] 2xl:top-[20%]"
            >
              <PagesPopUp show={show} update={updateHidden} />
            </div>
          </>
        )}
      </div>
    </QueryProvider>
  );
};

export default JobrequestFromBusinessToCreative;
