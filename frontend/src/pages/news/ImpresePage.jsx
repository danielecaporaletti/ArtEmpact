import React, {useState} from 'react'
import InputField from "../../atoms/InputField"
import Button from "../../atoms/Button"

export default function ImpresePage() {
  const [input, setInput] = useState("");
  
  const handleChange = (e) => {
    setInput(e.target.value);
  };




  return (
    <>
    <div className='flex flex-col justify-center items-center w-full pt-[30%] 2xl:pt-[10%] sm:pt-0 md:pt-[30%] lg:pt-[10%]'>
    <span className='text-center font-montserrat text-[1rem] font-[600]'>Non ci sono ancora articoli per questa categoria.<br/>Iscriviti alla newsletter per rimanere aggiornato sulle news di ArtEmpact.</span>
    <div className='w-[90%] pt-[1.8rem] sm:w-[30%] md:w-[60%] lg:w-[40%]'>
    <InputField type={"text"} name="Email-" value={input} label={"Email"} required={false} onChange={handleChange}/>
    </div>
    <div className='flex justify-center pt-[1rem]'>
    <Button type="submit" size={"small"}>
      <span className="text-[16px] font-bold">Iscriviti</span>
    </Button>
    </div>
    </div>
    </>
  )
}
