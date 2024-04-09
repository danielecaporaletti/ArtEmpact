import React from 'react'

export default function HeroRequest({ description, text }) {
  return (
    <>
    <form className="mx-auto">
    <div className="flex flex-col">
    <span className="text-black text-center font-semibold font-['Montserrat'] lg:text-[20px] text-[1.25rem] leading-normal pt-[2.75rem] resize-none">
    {text}</span>
    <p className="text-center pt-[1.69rem] lg:text-[16px] font-jost text-[1rem] text-slate font-normal leading-normal px-10 text-[#2F2F2F]">{description}</p>
    </div>
    </form>
   </>
  )
}
