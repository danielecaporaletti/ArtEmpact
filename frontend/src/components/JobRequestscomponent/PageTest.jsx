import IconVerified from "../../icons/IconVerified";

function Page({ background, name, description, hook, object, core }) {

  return (
    <div className="styledPage">
      <div className="pageMain">
      <div
        className="w-[9.375rem] h-[9.375rem] bg-cover self-center"
        style={{
          backgroundImage: `url(${background})`,
        }}
      ></div>
      <div className="w-auto flex flex-col relative">
        <div className="flex flex-row justify-center">
          <div className="h-[3rem] text-white text-[1.875rem] font-montserrat font-bold tracking-[.5px] underline underline-offset-[5px] decoration-[3px] text-center leading-none">
            {name}
          </div>
          <div className="h-4 aspect-w-1 aspect-h-1 flex justify-center">
            <IconVerified />
          </div>
        </div>
        <div className="text-white font-jost font-normal text-sm tracking-wide leading-normal text-center lg:mt-[0rem] mt-[1rem]">
          {description}
        </div>
      </div>
      <div className="flex flex-col items-center justify-center mt-[3.38rem] text-white">
        <div className="font-jost h-30 text-xs font-normal">
          <p>{hook}</p>
        </div>
        <div className="text-center text-shadow-sm font-montserrat text-lg font-bold">
          <p>{object}</p>
          <p>{core}</p>
        </div>
        <div className="flex gap-2 text-center text-shadow-sm font-montserrat text-lg font-bold lg:mt-[3rem] mt-[0.5rem]">
          <p>______</p>
          <p>______</p>
          <p>______</p>
        </div>
      </div>
      </div>
    </div>
  );
}

export default Page;
