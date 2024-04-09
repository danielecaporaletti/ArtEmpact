import React from "react";

import BusinessCardRegistration from "../../../components/BusinessCardRegistration";

const dataList = [
  {
    id: 1,
    title: "🎨 Un creativo",
    text: (
      <p>
        <span className="font-bold leading-10">🎨 Un creativo</span>
        <br />
        Trova il creativo giusto e fai della creatività la tua leva competitiva:
        <br /> Descrivi il posto vacante, le necessità, il tipo di contratto e
        la fascia di prezzo
        <br /> I creativi ti invieranno i loro CV o condivideranno la tua
        richiesta con il creativo più adatto nel loro network
      </p>
    ),
  },
];

const CreationBusinessCards = () => {
  return (
    <section className="flex justify-center min-h-screen">
      <BusinessCardRegistration data={dataList} />

      {/* <Link
        to="/home/business/job-request-from-business-to-creative"
        style={linkStyle}
      >
        Stai cercando un creativo? Crea questa card.
      </Link>
      <Link to="/home/business" style={linkStyle}>
        Vuoi andare alla tua home?
      </Link> */}
    </section>
  );
};

export default CreationBusinessCards;
