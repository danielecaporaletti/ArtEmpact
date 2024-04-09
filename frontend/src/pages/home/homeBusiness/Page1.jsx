//Page1.jsx
import IconVerified from "../../../icons/IconVerified";
import background from "./giulia.png";

function Page1({ data}) {
  const user = {
    name: "Giulia98",
    description: data?.data.compatibilityCard.title,
  };

  return (
    <>
      <div
        className="w-[9.375rem] h-[9.375rem] bg-cover self-center"
        style={{
          backgroundImage: `url(${background})`,
        }}
      ></div>
      <div className="w-auto flex flex-col relative">
        <div className="flex flex-row justify-center">
          <div className="h-[3.0625rem] text-white text-[2.5rem] font-montserrat font-bold tracking-[.5px] underline underline-offset-[5px] decoration-[3px] text-center leading-none">
            {user.name}
          </div>
          <div className="h-4 aspect-w-1 aspect-h-1 flex justify-center">
            <IconVerified />
          </div>
        </div>
        <div className="mt-1.25 h-5 text-white font-montserrat font-normal text-sm tracking-wide leading-normal text-center">
          {user.description}
        </div>
      </div>
      <div className="flex flex-col items-center justify-center mt-6.8 text-white">
        <div className="font-montserrat h-30 text-xs font-normal">
          <p>{user.hook}</p>
        </div>
        <div className="text-center text-shadow-sm font-montserrat text-lg font-bold">
          <p>{user.object}</p>
          <p>{user.core}</p>
        </div>
      </div>
    </>
  );
}

export default Page1;
