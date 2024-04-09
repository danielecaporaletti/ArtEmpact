import React, { useState } from "react";

const useToggle = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => {
    setIsOpen(!isOpen);
  };
  return { isOpen, setIsOpen, toggle };
};

export default useToggle;
