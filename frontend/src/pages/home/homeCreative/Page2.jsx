//Page2.jsx

function Page2({ data }) {
  const user = {
    chiSono:
    data?.data.compatibilityCard.companyVisionMission,
  };
  return (
    <div className="flex flex-col justify-start items-start h-full">
      <div className="text-white font-montserrat h-6 w-full font-bold">
        <p>👤 Chi siamo</p>
      </div>
      <div className="p-2 mt-4 w-full text-white font-jost text-base font-normal">
        <p>{user.chiSono}</p>
      </div>
    </div>
  );
}

export default Page2;
