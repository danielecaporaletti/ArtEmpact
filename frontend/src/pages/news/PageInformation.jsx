import React from 'react'
import IconBack from "../../icons/IconBack"
import {FaFacebook, FaInstagram, FaLink} from "react-icons/fa"

export default function PageInformation({image, text, onClick, hidden}) {
  return (
    <>
<div className='h-full w-full flex flex-col justify-center items-center pt-4 sm:w-[80%] sm:mx-auto' hidden={hidden}>
<div className='relative left-0 flex justify-start items-end w-full pt-8 ml-[10%]'>
<div className='absolute flex items-center gap-4 right'>
    <div className="ml-1">
    <button className="bg-transparent text-primary-color text-[16px] font-[500]" onClick={onClick}><IconBack /></button>
    </div>
    <div>
    <p className="text-primary-color text-[16px] font-[500]">Indietro</p>
    </div>
    </div>
    </div>
    <div className='w-[90%]'>
      <p className='pt-[1rem] font-montserrat text-[1rem] font-[700] leading-[1.2rem] sm:text-center'>{text}</p>
      <div className='flex items-center justify-end gap-2'>
      <p className='font-montserrat text-[0.9rem] text-right font-[700]'>Condividi </p>
      <ul>
        <li className='inline-block px-2'>
        <FaFacebook size={18}/> 
        </li>
        <li className='inline-block px-2'>
       <FaInstagram size={18} /> 
       </li>
        <li className='inline-block px-2'>
        <FaLink size={18} />
        </li>
      </ul>
      </div>
      <img src={image} alt="" className='w-[90%] h-[auto] rounded-[20px] mx-auto sm:w-[30%]' />
      <p className='pt-[1rem] font-montserrat text-[1rem] font-[700] leading-[1.2rem] sm:text-center'>{text}</p>
      <div className='h-[10%] w-[80%] overflow-y-auto sm:h-[30%] pt-[0.69rem] mx-auto'>
      <p className='pt-[1rem] font-jost text-[0.875rem] font-[400] leading-normal text-left'>
      Come e perché dovresti investire in un album musicale invece di un singolo? Quali sono i costi e i benefit, ma soprattutto quali sono le <br/> differenze? Scoprilo con il nostro articolo.<br/>
      </p>
      <p className='pt-[2rem] font-jost text-[0.875rem] font-[400] leading-normal text-left'>
      La musica è una forma d’arte con una storia molto antica che risale all’inizio della civiltà umana, ha il potere di toccare l’anima delle <br/>persone e di commuoverle emotivamente. Essa ha la capacità di farci viaggiare lontano, trasportandoci in un altro luogo e in un altro tempo. La musica è un linguaggio universale che può essere apprezzato da persone di ogni età e provenienza e non dipende dal contesto culturale di appartenenza. Quando si parla di musica al giorno d’oggi, esistono due forme principali in cui essa viene dispensata al pubblico: le canzoni singole e gli album. Sebbene entrambe queste forme siano popolari, esistono alcune differenze fondamentali che vale la pena approfondire. Un brano singolo è una composizione musicale a sé stante che di solito viene pubblicata come download digitale o come parte di un album più grande. I singoli sono spesso utilizzati per promuovere un album in uscita o per presentare un nuovo artista al pubblico. Sono anche spesso utilizzati come mezzo per generare un po’ di rumore intorno ad un particolare artista o a una canzone, una sorta di preview insomma. Un album, invece, è una raccolta di composizioni musicali che in generale vengono pubblicate insieme come opera completa. La lunghezza degli album può variare da poche canzoni a oltre 20 brani ed essi possono durare una ventina di minuti ma anche più di un’ora. Spesso hanno un tema o un concetto centrale che lega le canzoni tra loro e sono tipicamente pubblicati su un supporto fisico come un CD o, per i più esigenti, su un disco in vinile.
      </p>
      </div>
      </div>
    </div>
</>
  )
}
