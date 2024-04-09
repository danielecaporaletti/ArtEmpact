import React from 'react'
import ImpresePage from '../../pages/news/ImpresePage';

export default function PaginationNews ({buttonClicked, setButtonClicked, hidden}) {
  
  
  const handleButtonClick = (index) => {
    const updatedButtons = [...buttonClicked];
    updatedButtons[index] = !updatedButtons[index]
    setButtonClicked(updatedButtons)
  }
  

  return (
    <>
    <div className='pt-[3rem]' hidden={hidden}>
    <ul className='flex justify-center items-start sm:gap-[2.1875rem] gap-[1rem]'>
            <li key={1}>
                <button className={`rounded-[0.5625rem] bg-${buttonClicked[0] ? "bg-white text-[#99B8DD] border border-[#99B8DD]" : "[#99B8DD] text-white"} font-[400] font-jost text-[0.75rem] text-center px-[1.0625rem]`} onClick={() =>handleButtonClick(0)}>Marketing</button>
            </li>
            <li key={2}>
                <button className={`rounded-[0.5625rem] bg-${buttonClicked[1] ? "bg-white text-[#99B8DD] border border-[#99B8DD]" : "[#99B8DD] text-white"} font-[400] font-jost text-[0.75rem] text-center px-[1.0625rem]`} onClick={() => handleButtonClick(1)}>Artisti</button>
            </li>
            <li key={3}>
                <button className={`rounded-[0.5625rem] bg-${buttonClicked[2] ? "bg-white text-[#99B8DD] border border-[#99B8DD]" : "[#99B8DD] text-white"} font-[400] font-jost text-[0.75rem] text-center px-[1.0625rem]`} onClick={() => handleButtonClick(2)}>Imprese</button>
            </li>
    </ul>
    </div>
    {buttonClicked[2] ? 
    <div>
      <ImpresePage />
    </div>
    : null
    }
    </>
  )
}
