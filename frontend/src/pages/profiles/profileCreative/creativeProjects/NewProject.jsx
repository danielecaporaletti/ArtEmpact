import React, { useState } from "react";
import NewProjects from "../../../../components/NewProjects";
import NotificationCenter from "../../../../components/NotificationCenter";
import useToggle from "../../../../hooks/useToggle";
import { ProjectProvider } from "../../../../contexts/ProjectContext";

const NewProject = () => {
  const { isOpen, toggle } = useToggle();

  return (
    <ProjectProvider>
      <div className="grid grid-cols-1 md:grid-cols-4 bg-white">
        <div className="hidden md:block md:col-span-1 bg-gray-500">sidebar</div>
        <div className="flex justify-center md:justify-start md:pl-20 lg:pl-40 p-4 w-full md:col-span-3 bg-white min-h-screen">
          <NewProjects handlerClick={toggle} />
          {isOpen && <NotificationCenter />}
        </div>
      </div>
    </ProjectProvider>
  );
};

export default NewProject;
