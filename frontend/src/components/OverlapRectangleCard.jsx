//OverlapRectanglecard.jsx
function OverlapRectangleCard({ children }) {
  return (
    <div className="styledOverlap">
      <div className="styledRectangleOrange"></div>
      <div className="styledRectangleViolet"></div>
      <div className="styledRectangleSky">{children}</div>
    </div>
  );
}

export default OverlapRectangleCard;

