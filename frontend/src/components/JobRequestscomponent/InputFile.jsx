import React from 'react';
import { PiUploadFill } from "react-icons/pi";


export default function InputFile({type, accept, text, onChange, video }) {
  return (
    <>
    <div className="flex sm:flex-row sm:items-center sm:gap-4 pt-[0.75rem] flex-col justify-center items-center">
    <div className='lg:ml-[22rem] flex justify-center'>
    <div className='w-[80%] sm:w-[auto] h-auto'>
    {video && (
          <video width="400" height="300" controls>
            <source src={URL.createObjectURL(video)} type="video/mp4" />
          </video>
        )}
        </div>
        </div>
    <div className='border border-[#99B8DD] rounded-[14px] w-[11.25rem] h-[17.6875rem]' hidden={video}></div>
        <div className="text-center relative flex items-center pt-[1.88rem] gap-2 lg:mr-[20rem] justify-center">
          <div className="lg:text-[16px] text-[1rem] text-[#99B8DD] text-center font-['Jost'] font-[500] cursor-pointer"><PiUploadFill/></div>
          <p className="lg:text-[16px] text-[1rem] text-[#99B8DD] text-center font-['Jost'] font-[500] cursor-pointer">{text}</p>
      <input type={type} accept={accept} onChange={onChange} className="block h-full w-full absolute opacity-0 top-0 bottom-0 left-0 right-0"/>
    </div>
        </div>
        </>
  )
}
