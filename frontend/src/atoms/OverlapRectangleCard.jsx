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

// *********************************************************************************************************
// const StyledOverlap = styled.div`
//   position: relative;
//   height: 100%;
//   width: 100%;
// `;
// const StyledRectangleA = styled.div`
//   position: absolute;
//   height: 100%;
//   background-color: var(--color-extra);
//   border-radius: 5.61vh 5.61vh 0px 0px;
//   box-shadow: 0px 0.74vh 10px #0000002b;
//   filter: blur(0.74vh);
//   /* top: 2.05vh; */
//   /* left: 4.68vh; */
//   transform: rotate(3.23deg);
//   width: 100%;
// `;

// const StyledRectangleB = styled.div`
//   position: absolute;
//   background: linear-gradient(
//     180deg,
//     rgb(98, 59, 255) 0%,
//     rgb(98, 59, 255) 0.01%,
//     rgba(15, 83, 184, 0) 99.99%,
//     rgb(255, 255, 255) 100%
//   );
//   border-radius: 5.61vh 5.61vh 0px 0px;
//   box-shadow: 0px 0.74vh 10px #0000002b;
//   height: 100%;
//   /* left: 4.68vh; */
//   /* opacity: 0.8; */
//   filter: blur(0.74vh);
//   /* top: 2.06vh; */
//   transform: rotate(-3.86deg);
//   width: 100%;
// `;

// const StyledRectangleC = styled.div`
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   position: absolute;
//   background: linear-gradient(
//     180deg,
//     rgb(153, 184, 221) 44.27%,
//     rgb(255, 255, 255) 100%
//   );
//   border-radius: 5.61vh 5.61vh 0px 0px;
//   box-shadow: 0px 4px 10px #0000002b;
//   height: 100%;
//   /* left: 4.68vh; */
//   /* top: 2.06vh; */
//   width: 100%;
// `;
