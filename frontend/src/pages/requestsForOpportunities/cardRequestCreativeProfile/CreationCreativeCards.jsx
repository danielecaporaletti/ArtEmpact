import React from "react";

import CreativeCardRegistration from "../../../components/CreativeCardRegistration";
const dataList = [
  {
    id: 0,
    title: "💼 Un lavoro",
    text: (
      <span>
        <span className="font-bold leading-10">💼 Un lavoro</span>
        <br /> Su ArtEmpact puoi trovare i founder o i Team degli HR delle
        aziende o delle startup che ti interessano.
        <br /> Completa il tuo profilo, inserisci il tuo background e i tuoi
        progetti.L'algoritmo mostrerà la tua richiesta alle persone che stanno
        cercando figure come te
      </span>
    ),
  },
  {
    id: 1,
    title: "🎨 Un collaboratore",
    text: (
      <span>
        <span className="font-bold leading-10">🎨 Un creativo</span>
        <br />
        Trova il creativo giusto e fai della creatività la tua leva competitiva:
        <br /> Descrivi il posto vacante, le necessità, il tipo di contratto e
        la fascia di prezzo
        <br /> I creativi ti invieranno i loro CV o condivideranno la tua
        richiesta con il creativo più adatto nel loro network
      </span>
    ),
  },
];

const CreationCreativeCards = () => {
  return (
    <section className="flex justify-center min-h-screen">
      <CreativeCardRegistration data={dataList} />
    </section>
  );
};

export default CreationCreativeCards;
