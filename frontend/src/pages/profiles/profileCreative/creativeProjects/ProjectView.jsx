import React from "react";
import ProjectPreview from "../../../../components/ProjectPreview";
import { useNavigate } from "react-router-dom";
import NotificationCenter from "../../../../components/NotificationCenter";
import useToggle from "../../../../hooks/useToggle";
import { project } from "../../../../data/UserData";

const ProjectView = () => {
  const { isOpen, toggle } = useToggle();
  const navigate = useNavigate();
  const backToProfile = () => {
    navigate("/home/creative/profile");
  };

  return (
    <div className="flex justify-center p-4 w-full bg-white min-h-screen">
      <ProjectPreview
        {...project}
        backToProfile={backToProfile}
        handlerClick={toggle}
      />
      {isOpen && <NotificationCenter />}
    </div>
  );
};

export default ProjectView;
