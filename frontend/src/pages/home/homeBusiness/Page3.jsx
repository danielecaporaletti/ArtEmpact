//Page3.jsx

function Page3() {
  return (
    <div className="flex flex-col justify-start items-start h-full">
      <div className="text-white font-montserrat h-6 w-full font-bold">
        <p>L'Offerta'</p>
      </div>
      <div className="p-2 mt-4 w-full text-white font-jost text-base font-normal">
        <p>
          Ricerchiamo uno scultore per la produzione di 2 elaborati in gesso
          delle dimensioni di 35x25x40cm per un progetto attivo con un nostro
          cliente. Contratto di prestazione occasionale della durata di un mese
        </p>
      </div>
      <div className="p-2 text-white font-jost text-base font-light">
        <ul className="mt-4">
          <li className="mb-2">📚 Diploma superiore</li>
          <li className="mb-2">💼 1-2 anni di esperienza</li>
          <li className="mb-2">💸 3.000 - 5.000</li>
          {/* <li className="mb-2">📍 Da Remoto - Ibrido</li> */}
        </ul>
      </div>
    </div>
  );
}

export default Page3;
