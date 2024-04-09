import OverlapRectangleSimple from "../../atoms/OverlapRectangleSimple";
import IconHeart from "../../icons/IconHeart";
import IconLanguage from "../../icons/IconLanguage";
import PageTest from "../JobRequestscomponent/PageTest";


// la carta su phone con schermo piccolo e' da restringere
function CardTest({ children, color, background, name, description, hook, object, core  }) {
  return (
    <div className="styledCard">
      <OverlapRectangleSimple color={color}>
        <div className="optionBlock">
          <div className="containerIconSingle h-[3.4rem] mr-[0.9rem]">
            <IconLanguage color={"var(--color-white)"} size={"100%"} />
          </div>
          <div className="containerIconSingle">
            <IconHeart color={"var(--color-white)"} />
          </div>
        </div>
        <PageTest count={1} setCount={null} maxPage={null} background={background} name={name} description={description} hook={hook} object={object} core={core}/>
      </OverlapRectangleSimple>
    </div>
  );
}

export default CardTest;
