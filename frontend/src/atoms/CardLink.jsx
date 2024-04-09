
import React from "react";

// eslint-disable-next-line no-unused-vars
const dataList = [
  {
    title: "💼 Un lavoro",
    text: (
      <span>
        <span className="font-bold">💼 Un lavoro</span>
        <br /> Su ArtEmpact puoi trovare i founder o i Team degli HR delle
        aziende o delle startup che ti interessano.
        <br /> Completa il tuo profilo, inserisci il tuo background e i tuoi
        progetti.L'algoritmo mostrerà la tua richiesta alle persone che stanno
        cercando figure come te
      </span>
    ),
  },
  {
    title: "🎨 Un creativo",
    text: (
      <span>
        <span className="font-bold mb-8">🎨 Un creativo</span>
        <br />
        Trova il creativo giusto e fai della creatività la tua leva competitiva:
        <br /> Descrivi il posto vacante, le necessità, il tipo di contratto e
        la fascia di prezzo
        <br /> I creativi ti invieranno i loro CV o condivideranno la tua
        richiesta con il creativo più adatto nel loro network
      </span>
    ),
  },
  {
    title: "🏆 Un esperto",
    text: (
      <span>
        <span className="font-bold">🏆 Un esperto</span>
        <br /> Trova il professionista con le competenze che cerchi.
        <br /> Hai bisogno di un grafico o di un video maker? Questa è la
        sezione che fa per te! <br />
        Descrivi il posto vacante, le necessità, il tipo di contratto e la
        fascia di prezzo per trovare l'esperto più adatto alle tue esigenze.
      </span>
    ),
  },
];

const CardLink = ({ index, setIndex, data }) => {
  return (
    <>
      <div className="flex flex-col justify-center gap-3 w-full md:w-[485px] mb-8">
        {data.map((item, i) => {
          return (
            <div
              onClick={() => setIndex(i)}
              className={`${
                index === i ? "bg-[#99B8DD] h-full " : "bg-[#c2bdbd] h-[52px]"
              }   rounded-md px-4 py-4 flex items-center  overflow-hidden transition-all ease-in-out duration-300 `}
              key={item.title}
            >
              {index !== i ? (
                <span className="text-[20px] font-bold text-[#2F2F2F]">
                  {item.title}
                </span>
              ) : (
                <p className="text-[13px] font-montserrat text-white leading-6">
                  {item.text}
                </p>
              )}
            </div>
          );
        })}
      </div>
    </>
  );
};

export default CardLink;
