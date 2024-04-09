//HomeBusinessCard.jsx
import IconHeart from "../../../icons/IconHeart";
import IconLanguage from "../../../icons/IconLanguage";

import Page from "./Page";
import OverlapRectangleCard from "../../../components/OverlapRectangleCard";

// la carta su phone con schermo piccolo e' da restringere
function HomeBusinessCard({ count, setCount, maxPage, children, data }) {
  return (
    <div className="styledCard">
      <OverlapRectangleCard>
        <div className="optionBlock">
          <div className="containerIconSingle h-[3.4rem] mr-[0.9rem]">
            <IconLanguage color={`var(--color-white)`} size={"100%"} />
          </div>
          <div className="containerIconSingle">
            <IconHeart color={`var(--color-white)`} />
          </div>
        </div>
        <Page count={count} setCount={setCount} maxPage={maxPage} data={data}/>
      </OverlapRectangleCard>
    </div>
  );
}

export default HomeBusinessCard;
