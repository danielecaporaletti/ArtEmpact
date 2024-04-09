import React from "react";

const TextArea = ({ value, name, onChange }) => {
  return (
    <textarea
      value={value}
      name={name}
      onChange={onChange}
      id="message"
      rows="5"
      className="w-full border-b-2 text-sm bg-transparent  px-2 py-3"
      placeholder="🖊️ Descrizione*"
    />
  );
};

export default TextArea;
