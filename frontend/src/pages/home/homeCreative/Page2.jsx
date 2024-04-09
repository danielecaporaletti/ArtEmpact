//Page2.jsx

function Page2() {
  const user = {
    chiSono:
      " Lorem ipsum dolor sit amet consectetur. Adipiscing justo ac justo tellus proin. Tempor tempus cursus egestas vel egestas fermentum nibh turpis viverra. Pulvinar cras vitae enim facilisis. Turpis curabitur amet risus mattis. Cras ut potenti nisl eleifend risus. Consequat lobortis ultrices hac egestas at.",
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
