import React from 'react'
import logo from '../../pages/requestsForOpportunities/cardRequestCreativeProfile/JobrequestFromCreativeToBusiness/pagesCreative/normal 3.png';
import IconSearch from "../../icons/IconSearch";

export default function Searchbar({ onKeyDown, onChange, value, onClick, placeholder}) {
  return (
    <div className='flex justify-center items-center lg:w-[22.75rem] px-[0.75rem] py-[0.56rem] bg-white rounded-[3.1875rem] shadow-btn-vert w-[18rem]'>
      <img src={logo} alt="art" className='w-[1.8125rem] h-[1.6875rem]'/>
          <input type="text" id="searchbar" placeholder={placeholder} className='w-[18rem] text-left h-[1.5rem] ml-[0.56rem] border-b border-[#413A33] font-[Roboto] text-[1rem]' onChange={onChange} onKeyDown={onKeyDown} value={value} />
          <button className='text-[0.9375rem]' onClick={onClick}>
          <IconSearch color={"#413A33"} />
          </button>
    </div>
  )
}
