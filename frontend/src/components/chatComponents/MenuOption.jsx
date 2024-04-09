import React from 'react'
import IconBack from "../../icons/IconBack";
import { AiOutlineStop } from "react-icons/ai";
import { BsExclamationOctagonFill } from "react-icons/bs"
export const MenuOption = ({isOpen, id, onClick}) => {

  return (
    <div className='relative py-4 gap-6'>
     <div className={`${isOpen[id] ? "open" : "exiting"} bg-white w-[80%] h-[5rem] absolute text-[1rem] text-[#623BFF] justify-center items-center z-10 menu`}>
     <div className='absolute flex'>
    <div className="lg:pt-5 lg:ml-5 ml-5 pt-6">
    <button className="bg-transparent text-primary-color text-[16px] font-[500] text:hidden" onClick={onClick}><IconBack /></button>
    </div>
    </div>
     <div className='relative flex flex-col justify-center items-center top-[2.8rem]'>
        <div className='absolute items-center left-[5rem]'>
        <BsExclamationOctagonFill />
        <p className='text-black text-[0.875rem] font-montserrat font-[500] text-center pt-[0.5rem]'>segnala</p> 
        </div>
        </div>
        <div className='relative flex flex-col justify-center items-center top-[2.8rem]'>
        <div className='absolute items-center left-[13rem]'>
        <AiOutlineStop />
        <p className='text-black text-[0.875rem] font-montserrat font-[500] pt-[0.2rem]'>blocca</p> 
        </div>
        </div>
    </div>
    <style>
      {`
      @media screen and (max-width: 280px) {
        .menu {
          width: 98%;
        }
      }`}
      
    </style>
    </div>
  )
}
