import React from "react";
import ArtistPreview from "../../../components/ArtistPreview";
import { useParams, useNavigate } from "react-router-dom";
import { users } from "../../../data/UserData";
import useToggle from "../../../hooks/useToggle";
import NotificationCenter from "../../../components/NotificationCenter";

const ArtistsPreview = () => {
  const navigate = useNavigate();
  const { isOpen, setIsOpen, toggle } = useToggle();
  const { id } = useParams();
  const user = users.filter((user, i) => {
    return user.id == id;
  });

  const backPage = () => {
    navigate(-1);
  };
  return (
    <div className="flex justify-center p-4 w-full bg-white min-h-screen">
      {user.map((el) => {
        return (
          <ArtistPreview
            {...el}
            key={el.id}
            toggle={toggle}
            backPage={backPage}
          />
        );
      })}
      {isOpen && <NotificationCenter />}
    </div>
  );
};

export default ArtistsPreview;
