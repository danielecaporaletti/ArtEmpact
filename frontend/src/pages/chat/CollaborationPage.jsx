import React, { useState} from 'react'
import { ButtonEmpty } from '../../components/chatComponents/ButtonEmpty'
import Group from "../../assetTemporanei/Group 132.png"
import Lolly from "../../assetTemporanei/Lolly.png"

const CollaborationPage = ({onClick }) => {
  const [buttonColor, setButtonColor] = useState("Richieste");
  const [visible, setVisible] = useState(true)
  
  const handleClickColor = (buttonName) => {
    setButtonColor(buttonName)
    if (buttonName === "Rifiutate") {
      onClick(buttonName)
    } 
  }

  return (
    <>
    <div className='pt-[2rem] flex flex-col justify-center px-[8%]'>
        <span className='font-montserrat font-[500] text-[1rem]'>Richieste di collaborazione</span>
        <div className='pt-[1rem] lg:w-full w-[96%]'>
        <p className='text-[0.875rem] font-[300] text-black font-jost'>Qui trovi le richieste di collaborazione che le aziende con le quali hai matchato ti hanno inviato, sei libero di accettarle <br/> 
        o rifiutarle in base al tuo interesse!</p>
        </div>
        </div>
        <div className='flex gap-[1rem] pt-[3rem] justify-center'>
        <ButtonEmpty text={"Richieste"} color={buttonColor === "Richieste" ? "bg-[#623BFF] text-white" : "bg-transparent text-[#623BFF]"}  onClick={() => handleClickColor("Richieste")}/>
        <ButtonEmpty text={"Inviate"}  color={buttonColor === "Inviate" ? "bg-[#623BFF] text-white" : "bg-transparent text-[#623BFF]"} onClick={() => handleClickColor("Inviate")}/>
        <ButtonEmpty text={"Rifiutate"} onClick={() => handleClickColor("Rifiutate")} color={buttonColor === "Rifiutate" ? "bg-[#623BFF] text-white" : "bg-transparent text-[#623BFF]"} />
        </div>
        {buttonColor === "Richieste" && !visible && (
         <div className='flex flex-col items-center pt-[5rem] font-semibold'> 
         <span className='text-center font-montserrat text-[1.2rem]'>Non sono ancora presenti <br/> richieste di collaborazioni. </span>
        </div>
      )}
        {buttonColor === "Richieste" && visible && (
  <div className='flex flex-col items-start ml-[1rem] pt-[3rem]'> 
    <ul>
      <li>
        <div className="flex gap-3">
          <img src={Group} alt="" className="rounded-full h-[4.75rem] w-[4.75rem]"/>
          <div className="flex flex-col">
            <span className="text-left text-[1rem] font-montserrat text-[#2F2F2F] font-[500]">Medi srl</span>
            <span className="text-[0.8125rem] font-[300] text-[#2F2F2F]">Siamo un'azienda che produce prodotti <br/> elettronici che ricerca un'artista visiva. </span>
          </div>
        </div>
        <div className='flex pt-[0.3rem] gap-6 justify-end items-end w-full ml-[8%] gap-button'>
          <button className='border border-[#623BFF] w-[auto] h-[1.3rem] bg-transparent leading-normal font-montserrat font-[500] text-[#623BFF] text-[0.7rem] rounded-[0.4rem] px-2 active:bg-[#623BFF] active:text-white' onClick={() => setVisible(false)}>Accetta richiesta</button>
          <button className='underline underline-offline-2 w-[auto] h-[1.3rem] bg-transparent leading-normal font-montserrat font-[500] text-[#623BFF] text-[0.7rem] rounded-[0.4rem] px-2' onClick={() => setVisible(false)}>Rifiuta richiesta</button>
        </div>
      </li>
    </ul>
  </div>
)}
<style>
  {`
  @media screen and (max-width: 280px) {
    .gap-button {
      margin-left: 2%;
  }
  
  }
  `}
</style>
        {buttonColor === "Inviate" && (
        <div className='flex flex-col items-start pt-[3rem] ml-[1rem]'>
        <ul>
          <li>
          <div className="flex gap-3 w-[95%]">
          <img src={Lolly} alt="" className="rounded-full h-[4.75rem] w-[4.75rem]"/>
          <div className="flex flex-col">
          <span className="text-left text-[1rem] font-montserrat text-[#2F2F2F] font-[500]">Lolly72$</span>
          <span className="text-[0.8125rem] font-[300] text-[#2F2F2F]">Ciao Daniele, vorrei proporti una collaborazione con me e altri artisiti co...</span>
          </div>
          </div>
          </li>
        </ul>
      </div>
      )}


    
    </>
  )
}

export default CollaborationPage