import React from 'react'

export default function PagesPopUp({show, update}) {

  return (
    <div className="absolute">
    <div className="bg-white rounded-t-[30px] sm:w-[390px] w-full h-auto p-5">
    <h4 className="text-[70%] font-semibold font-['Montserrat'] text-center leading-normal">Quale pagina vuoi aggiungere?</h4>
    <ul className="text-[#2F2F2F] text-[1rem] font-normal leading-normal list-none px-5 pt-3">
        <li className='p-2'>
        📃 Pagina di descrizione
        </li>
        <li className={`p-2 ${!show ? "opacity-[0.5]" : ""}`}>
        <button disabled={!show} onClick={() => update()}>🤳🏼 Video intro</button>
        </li>
        <li className="opacity-[0.5] cursor-default p-2">
        🔗 Link esterno 
        </li>
    </ul>
    </div>
    </div>
  )
}
