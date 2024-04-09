import { PiTrashSimpleLight } from "react-icons/pi";

export default function NavigationRequests({ pages, names, hidden, toggleDropdown, setPages, show, update }) {

  return (
    <>
    <nav className={`pb-5`} hidden={hidden}>
        <div className="overflow-x-auto max-[600px]:whitespace-nowrap list-none flex sm:justify-center sm:gap-2 gap-2 pl-10 sm:pl-0">
          <li key={0}  className='inline-block'>
          <button className={`rounded-2xl lg:w-[84px] font-['Jost'] border-0 text-[0.75rem] w-[5rem] font-normal 
            ${pages === 0 ? "bg-primary-color text-white" : "bg-white text-primary-color"}`}
            onClick={() => setPages(0)}>Titolo</button>
          </li>
          <li key={1}  className='inline-block relative'>
          <div className="absolute top-0 left-0 h-full flex items-center justify-start ml-[0.1rem]">
          <button className={`${pages === 1 ? "text-white" : "text-primary-color"} gap-0 px-0 relative top-[12%]`} hidden={update} onClick={show} ><PiTrashSimpleLight size={12}/></button>
          </div>
          <button className={`rounded-2xl lg:w-[84px] font-['Jost'] border-0 text-[0.75rem] w-[5rem] font-normal 
            ${pages === 1 ? "bg-primary-color text-white" : "bg-white text-primary-color"}`}
            onClick={() => setPages(1)} hidden={update}>Video Intro</button>
          </li>
           {names.map((button) => (
           <li key={button.id} className='inline-block'>
           <button className={`rounded-2xl lg:w-[84px] font-['Jost'] border-0 text-[0.75rem] w-[5rem] font-normal 
            ${pages === button.id ? "bg-primary-color text-white" : "bg-white text-primary-color"}`}
            onClick={() => setPages(button.id)}>
             {button.title}
            </button>
           </li>
           ))}
           <li>
           <button type="button" className={`border-0 rounded-full text-[0.75rem] text-white bg-[#623bff] gap-x-1 w-[1.188rem] h-[1.188rem]`} 
           onClick={() => toggleDropdown()} 
           >+</button>
           </li>
        </div>
    </nav>
    </>
  )
}
