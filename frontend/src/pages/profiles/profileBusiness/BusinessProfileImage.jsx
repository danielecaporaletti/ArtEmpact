import { GoArrowRight } from "react-icons/go";
import IconVerified from "../../../icons/IconVerified";

function BusinessProfileImage({ count, setCount }) {
  return (
    <div className="w-[23.0625rem] h-[23.0625rem] mt-[.94rem] relative">
      <img
        src="/src/icons/man.jpg"
        alt=""
        className="w-full h-full object-cover rounded-[1.875rem]"
      />
      {/* Banda opaca */}
      <div className="w-[23.3125rem] h-[4.625rem] bg-white/[.56] absolute bottom-0 leading-none">
        <div className="text-black font-montserrat font-bold tracking-[.5px] text-[1.1875rem] mt-[.94rem] pl-4 relative">
          Medi srl
          <div className="h-3.5 aspect-w-1 aspect-h-1 flex absolute top-0 left-[6.35rem]">
            <IconVerified color={`var(--color-primary-color)`} />
          </div>
          <p className="text-[.625rem] font-normal mt-[.31rem] jusifyy-center">
            Azienda Corporate
          </p>
        </div>
      </div>
      <div
        className="w-7 h-7 bg-grey-arrow rounded-full absolute top-[11.56rem] right-[.62rem]"
        onClick={() => setCount(count + 1)}
      >
        <GoArrowRight
          size={"65%"}
          style={{
            color: "#0f53b8",
            position: "absolute",
            top: "0.3rem",
            right: "0.3rem",
          }}
        />
      </div>
    </div>
  );
}

export default BusinessProfileImage;
