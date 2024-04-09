import React from "react";

const InputFile = ({ text, value, onChange }) => {
  return (
    <>
      <div>
        <label
          title="Click to upload"
          htmlFor="button2"
          className="flex items-center gap-2 mt-1 w-full h-[56px] sm:h-[48px] px-3 py-2 bg-white border border-[#99B8DD] rounded-xl text-sm shadow-sm placeholder-slate-400
      focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500
      disabled:bg-slate-50 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none
      invalid:border-pink-500 invalid:text-pink-600
      focus:invalid:border-pink-500 focus:invalid:ring-pink-500"
        >
          <div className="relative">
            <svg
              width="22"
              height="22"
              viewBox="0 0 22 22"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M3.16667 21.3333C2.43334 21.3333 1.80534 21.072 1.28267 20.5493C0.760002 20.0267 0.499113 19.3991 0.500002 18.6667V14.6667H3.16667V18.6667H19.1667V14.6667H21.8333V18.6667C21.8333 19.4 21.572 20.028 21.0493 20.5507C20.5267 21.0733 19.8991 21.3342 19.1667 21.3333H3.16667ZM9.83334 16V5.13333L6.36667 8.6L4.5 6.66667L11.1667 0L17.8333 6.66667L15.9667 8.6L12.5 5.13333V16H9.83334Z"
                fill="#99B8DD"
              />
            </svg>
          </div>
          <div className="relative">
            <span className="block text-xs font-medium text-slate-700">
              {text}
            </span>
          </div>
        </label>
        <input
          onChange={onChange}
          className="hidden"
          type="file"
          name="button2"
          id="button2"
        />
      </div>
    </>
  );
};

export default InputFile;
