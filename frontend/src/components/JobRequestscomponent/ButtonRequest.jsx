import React from 'react'

const ButtonRequest = ({ text, onClick, disabled, hidden }) => {
  return (
    <div className="flex justify-center items-center">
    <button className="rounded-[14px] w-[229px] h-[53px] sm:hover:bg-light-violet bg-primary-color sm:active:bg-primary-color text-white text-[16px] font-['Jost']" onClick={onClick} disabled={disabled} hidden={hidden}>{text}</button>
    </div>
  )
}

export default ButtonRequest
