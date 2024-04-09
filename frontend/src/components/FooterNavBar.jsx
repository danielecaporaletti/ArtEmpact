import { Link, useLocation } from "react-router-dom";
import IconHome from "../icons/IconHome";
import IconMessage from "../icons/IconMessage";
import IconElipse from "../icons/IconElipse";
import IconFind from "../icons/IconFind";
import IconNews from "../icons/IconNews";

function FooterNavBar({ activePage }) {
  const user = useLocation();
  console.log(user);
  return (
    <div className="w-[23.48542rem] h-[3.1873rem] flex px-3 py-[.38rem] rounded-[2.3rem] bg-white shadow-btn-vert justify-between items-center mx-auto">
      <Link
        to="/"
        className={`footer__icon-box fill-primary-color ${
          activePage === "home" ? "nav__link-active" : ""
        }`}
      >
        <div className="w-[1.5rem] translate-y-1.5">
          <IconHome />
        </div>
        <div className="w-full text-[.6rem] text-black invisible opacity-0">
          <span>home</span>
        </div>
      </Link>
      <Link
        to="/home/chat"
        className={`footer__icon-box stroke-primary-color ${
          activePage === "messaggi" ? "nav__link-active" : ""
        }`}
      >
        <div className="w-[1.5rem] translate-y-1.5">
          <IconMessage />
        </div>
        <div className="w-full text-[.6rem] text-black invisible opacity-0">
          <span>messaggi</span>
        </div>
      </Link>
      {user.pathname === "/home/business" ? (
        <Link
          to="/home/business/card-creation"
          className={`footer__icon-box fill-primary-color ${
            activePage === "cerca" ? "nav__link-active" : ""
          }`}
        >
          <div className="w-[1.5rem] translate-y-1.5">
            <IconFind />
          </div>
          <div className="w-full text-[.6rem] text-black invisible opacity-0">
            <span>cerca</span>
          </div>
        </Link>
      ) : (
        <Link
          to="/home/creative/card-creation"
          className={`footer__icon-box fill-primary-color ${
            activePage === "cerca" ? "nav__link-active" : ""
          }`}
        >
          <div className="w-[1.5rem] translate-y-1.5">
            <IconFind />
          </div>
          <div className="w-full text-[.6rem] text-black invisible opacity-0">
            <span>cerca</span>
          </div>
        </Link>
      )}

      <Link
        to="/home/news"
        className={`footer__icon-box fill-primary-color ${
          activePage === "news" ? "nav__link-active" : ""
        }`}
      >
        <div className="w-[1.5rem] translate-y-1.5">
          <IconNews />
        </div>
        <div className="w-full text-[.6rem] text-black invisible opacity-0">
          <span>news</span>
        </div>
      </Link>
      {user.pathname === "/home/business" ? (
        <Link
          to="/home/business/profile"
          className={`footer__icon-box fill-primary-color ${
            activePage === "profilo" ? "nav__link-active" : ""
          }`}
        >
          <div className="w-[1.5rem] translate-y-1.5">
            <IconElipse />
          </div>
          <div className="w-full text-[.6rem] text-black invisible opacity-0">
            <span>profilo</span>
          </div>
        </Link>
      ) : (
        <Link
          to="/home/creative/profile"
          className={`footer__icon-box fill-primary-color ${
            activePage === "profilo" ? "nav__link-active" : ""
          }`}
        >
          <div className="w-[1.5rem] translate-y-1.5">
            <IconElipse />
          </div>
          <div className="w-full text-[.6rem] text-black invisible opacity-0">
            <span>profilo</span>
          </div>
        </Link>
      )}
    </div>
  );
}

export default FooterNavBar;
