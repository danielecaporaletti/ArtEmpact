import React from 'react'

export const ButtonEmpty = ({text, onClick, color}) => {
  return (
    <>
        <button className={`border border-[#623BFF] w-[auto] h-[1.9375rem] px-2 ${color} leading-normal font-montserrat font-[500] text-[#623BFF] text-[0.875rem] rounded-[0.5625rem]`} onClick={onClick}>{text}</button>
    </>
  )
}
