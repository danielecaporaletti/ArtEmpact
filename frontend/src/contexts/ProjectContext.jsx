import { useState, useContext, createContext } from "react";

const ProjectContext = createContext();

const ProjectProvider = ({ children }) => {
  const [inputFile, setInputFile] = useState({
    imguno: "",
    imgdue: "",
    imgtre: "",
  });
  const [inputProject, setInputProject] = useState({
    nome: "",
    anno: "",
    tipo: "",
    descrizione: "",
    committente: "",
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
        nome: "",
        anno: "",
        tipo: "",
        descrizione: "",
        committente: "",
        link: "",
      });
    }
  };

  return (
    <ProjectContext.Provider
      value={{
        inputProject,
        setInputProject,
        handleChange,
        submit,
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
