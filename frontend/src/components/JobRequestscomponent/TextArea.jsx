const TextArea = ({onChange, value, text, number, maxLength}) => {
  return (
   <>
      <form className="mx-auto">
        <div className="flex flex-col">
        <span className="text-[#99B8DD] font-['Jost'] text-[0.8rem] font-[300] px-[1.75rem] sm:px-0">
        {text}/{number}
        </span>
        <textarea
          id="testo"
          value={value} 
          onChange={onChange}
          rows={4}
          cols={50}
          maxLength={maxLength}
          className="rounded-2xl lg:w-[50rem] lg:h-[116px] w-[90%] h-[7rem] font-normal text-[1rem] border border-[#99B8DD] p-4 resize-none mx-auto">
          </textarea>
        </div>
        </form>
      </>
  );
}

export default TextArea;