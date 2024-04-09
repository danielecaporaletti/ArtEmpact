import React from "react";
import ProjectImages from "../atoms/ProjectImages";
import Logo from "../atoms/Logo";
import IconBell from "../icons/IconBell";
import FooterNavBar from "./FooterNavBar";
import Button from "../atoms/Button";
import InputField from "../atoms/inputField";
import TextArea from "../atoms/TextArea";
import VerticalHeader from "../atoms/VerticalHeader";
import { Link } from "react-router-dom";
import { useProjectContext } from "../contexts/ProjectContext";

const NewProjects = ({ handlerClick }) => {
  const { inputProject, handleChange, submit, inputFile, handleFile } =
    useProjectContext();

  return (
    <>
      <div className="w-full max-w-md">
        <div className="relative flex justify-between items-center mt-8 mb-9 md:hidden">
          <Logo />
          <div
            onClick={handlerClick}
            className="w-[30px] h-[30px] rounded-full bg-white shadow-md p-2"
          >
            <IconBell color="blue" />
          </div>
        </div>
        <div className="md:block max-sm:hidden fixed top-1/4 right-9">
          <VerticalHeader onClick={handlerClick} />
        </div>
        <InputField
          size="small"
          text="💡 Nome del Progetto"
          value={inputProject.nome}
          name="nome"
          onChange={handleChange}
        />
        <div className="relative w-full flex gap-2 snap-x snap-mandatory overflow-x-auto py-4">
          <ProjectImages
            onChange={handleFile}
            nome="imguno"
            value={inputFile.imguno}
          />
          <ProjectImages
            onChange={handleFile}
            nome="imgdue"
            value={inputFile.imgdue}
          />
          <ProjectImages
            onChange={handleFile}
            nome="imgtre"
            value={inputFile.imgtre}
          />
        </div>
        <div className="flex flex-col gap-5">
          <InputField
            size="small"
            text="🗓️ Anno del Progetto*"
            value={inputProject.anno}
            name="anno"
            onChange={handleChange}
          />
          <InputField
            size="small"
            text="☑️ Tipo di progetto*"
            value={inputProject.tipo}
            name="tipo"
            onChange={handleChange}
          />
          <TextArea
            value={inputProject.descrizione}
            name="descrizione"
            onChange={handleChange}
          />
          <InputField
            size="small"
            text="👤 Committente (facoltativo)"
            value={inputProject.committente}
            name="committente"
            onChange={handleChange}
          />
          <InputField
            size="small"
            text="🔗 Link Esterno (Premium)"
            value={inputProject.link}
            name="link"
            onChange={handleChange}
          />
        </div>
        <div className="w-[229px] mt-6 mb-16 mx-auto">
          <Button handleClick={submit}>
            <Link to="/home/creative/profile/project-view">Pubblica</Link>
          </Button>
        </div>
        <div className="fixed w-full bottom-2 left-1/2 -translate-x-1/2 max-w-md px-4 md:hidden">
          <FooterNavBar />
        </div>
      </div>
    </>
  );
};

export default NewProjects;
