//Page.jsx
import { GoArrowRight, GoArrowLeft } from "react-icons/go";


import Page1 from "./Page1";
import Page2 from "./Page2";
import Page3 from "./Page3";

function Page({ count, setCount, maxPage, data }) {
  
 
  return (
    <div className="styledPage">
      <div className="arrowColumn ml-[.44rem] mr-[1rem]">
        {count !== 1 ? (
          <div
            className="h-7 w-7 bg-grey-arrow rounded-full absolute top-[18.56rem]"
            onClick={() => setCount(count - 1)}
          >
            <GoArrowLeft
              size={"65%"}
              style={{
                color: "var(--color-primary-color)",
                position: "absolute",
                top: "0.3rem",
                left: "0.3rem",
              }}
            />
          </div>
        ) : null}
      </div>
      <div className="pageMain">
        {count === 1 ? <Page1 data={data} /> : null}
        {count === 2 ? <Page2 data={data}/> : null}
        {count === 3 ? <Page3 data={data}/> : null}
      </div>

      <div className="arrowColumn mr-[.44rem]">
        {count !== maxPage ? (
          <div
            className="w-7 h-7 bg-grey-arrow rounded-full absolute top-[18.56rem]"
            onClick={() => setCount(count + 1)}
          >
            <GoArrowRight
              size={"65%"}
              style={{
                color: "var(--color-primary-color)",
                position: "absolute",
                top: "0.3rem",
                right: "0.3rem",
              }}
            />
          </div>
        ) : null}
      </div>
    </div>
  );
}

export default Page;
