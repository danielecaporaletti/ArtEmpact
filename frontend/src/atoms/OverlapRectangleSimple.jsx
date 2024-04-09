function OverlapRectangleSimple({ children, color }) {
  return (
    <div className="styledOverlap lg:w-[21.25rem] lg:h-[33.375rem] w-[90%] pb-0 sm:mx-0 mx-auto">
      <div className={`styledRectangleSky ${color}`}>{children}</div>
    </div>
  );
}

export default OverlapRectangleSimple;
