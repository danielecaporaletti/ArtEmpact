import React from 'react'

export default function CardNews({text, image, id, link, hidden }) {
  return (
    <div className='w-full flex flex-col justify-center items-center' hidden={hidden}>
      <img src={image} className='w-[90%] h-[auto] rounded-[20px] sm:w-[30%]' alt="" key={id} />
      <div className='w-[90%] sm:w-[25%]'>
      <p className='pt-[1rem] font-montserrat text-[1rem] font-[700] leading-[1.2rem]'>{text}</p>
      <div className='pt-[1rem] text-right underline text-[#623BFF] font-montserrat text-[0.75rem] font-light leading-normal cursor-pointer' onClick={link}>
        Leggi tutto l'articolo
      </div>
      </div>
    </div>
  )
}
