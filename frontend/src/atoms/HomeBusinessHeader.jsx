import IconCircle from "../icons/IconCircle";
import IconBell from "../icons/IconBell";
function HomeBusinessHeader() {
  return (
    <div className="w-full flex flex-row items-center justify-between mt-[1.31rem] mb-[1.75rem]">
      <div className="business__header-icon ml-[1.56rem]">
        <IconCircle color={`var(--color-primary-color)`} />
      </div>
      <div className="business__header-icon mr-[1.56rem]">
        <IconBell color={`var(--color-primary-color)`} />
      </div>
    </div>
  );
}

export default HomeBusinessHeader;
