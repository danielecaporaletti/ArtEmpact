import React from 'react';
import IconBack from "../../icons/IconBack";
export const BackPage = ({hidden, onClick}) => {
  return (
    <div className='absolute flex'>
    <div className="lg:pt-5 lg:ml-10 ml-5 pt-2">
    <button className="bg-transparent text-primary-color text-[16px] font-[500] text:hidden" hidden={hidden} onClick={onClick}><IconBack /></button>
    </div>
    <div className="ml-8 pt-7" hidden={hidden}>
    <p className="text-primary-color text-[16px] font-[500] hidden xl:block">Indietro</p>
    </div>
    </div>
  )
}

