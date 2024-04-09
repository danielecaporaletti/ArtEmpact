import { useState, useContext, createContext } from "react";

const ProjectContext = createContext();

const ProjectProvider = ({ children }) => {
  const [inputFile, setInputFile] = useState({
    imguno: "",
    imgdue: "",
    imgtre: "",
  });
  const [inputProject, setInputProject] = useState({
    name: "",
    year: "",
    type: "",
    description: "",
    customer: "",
    link: "",
  });
  const handleFile = (e) => {
    setInputFile({ ...inputFile, [e.target.name]: e.target.files });
  };
  const handleChange = (e) => {
    setInputProject({ ...inputProject, [e.target.name]: e.target.value });
  };
  const submit = (e) => {
    if (!inputProject) {
      e.preventDefault();
    } else {
      setInputProject({
        name: "",
        year: "",
        type: "",
        description: "",
        customer: null,
        link: null,
      });
    }
  };

  return (
    <ProjectContext.Provider
      value={{
        inputProject,
        setInputProject,
        handleChange,
        inputFile,
        setInputFile,
        handleFile,
      }}
    >
      {children}
    </ProjectContext.Provider>
  );
};

const useProjectContext = () => {
  return useContext(ProjectContext);
};

export { ProjectProvider, useProjectContext };
