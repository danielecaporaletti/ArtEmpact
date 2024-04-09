import React, { useState } from "react";
import image1 from "../../assetTemporanei/image 3News.png";
import image2 from "../../assetTemporanei/image4News.png";
import image3 from "../../assetTemporanei/image5News.png";
import image4 from "../../assetTemporanei/imageNews.png";
import image5 from "../../assetTemporanei/image 6News.png";
import image6 from "../../assetTemporanei/image7News.png";
import image7 from "../../assetTemporanei/image 8News.png";
import image8 from "../../assetTemporanei/image9News.png";
import PageInformation from "../news/PageInformation";
import LogoAE from "../../icons/LogoAE";
import PaginationNews from "../../components/newsComponents/PaginationNews";
import HeroNews from "../../components/newsComponents/HeroNews";
import CardNews from "../../components/newsComponents/CardNews";
import FooterNavBar from "../../components/FooterNavBar";
const News = () => {
  const [buttonClicked, setButtonClicked] = useState([false]);
  const [page, setPage] = useState(0);
  const [visible, setVisible] = useState(false);

  const handlePage = () => {
    setPage(page - 1);
  };

  const arrowPage = () => {
    setPage(page + 1);
  };

  const news = [
    {
      id: 1,
      image: image1,
      text: "Meglio puntare su un album musicale o il successo di un singolo?",
      link: (
        <PageInformation
          image={image1}
          text={
            "Meglio puntare su un album musicale o il successo di un singolo?"
          }
          hidden={page === 0}
          onClick={() => handlePage()}
        />
      ),
    },
    {
      id: 2,
      image: image2,
      text: "Musica e NFT: quale futuro?",
      link: "",
    },
    {
      id: 3,
      image: image3,
      text: "Intervista a Chat GPT sul futuro dell’arte tradizionale e digitale?",
      link: "",
    },
    {
      id: 4,
      image: image4,
      text: "Guida alla pubblicazione per artisti: Self-publishing VS Casa editrice di romanzi.",
      link: "",
    },
    {
      id: 5,
      image: image5,
      text: "6 consigli utili su come vendere arte online.",
      link: "",
    },
    {
      id: 6,
      image: image6,
      text: "Perché il networking è la tua ancora di salvezza nel mondo dell’Arte: 4 consigli utili",
      link: "",
    },
    {
      id: 7,
      image: image7,
      text: "Le 4 mostre “Made in Italy” che cambiarono la storia dell’arte.",
      link: "",
    },
    {
      id: 8,
      image: image8,
      text: "Le donne che hanno segnato il mondo dell’arte",
      link: "",
    },
  ];

  const handleClick = () => {
    if (buttonClicked[0] && !buttonClicked[1]) {
      return {
        text: news[4].text,
        key: news[4].id,
        image: news[4].image,
      };
    } else if (buttonClicked[1]) {
      return {
        text: news[6].text,
        key: news[7].id,
        image: news[6].image,
      };
    } else {
      return {
        text: news[0].text,
        key: news[0].id,
        image: news[0].image,
      };
    }
  };
  return (
    <div className="bg-white pb-20">
      <div className="bg-gradient-layout w-full justify-center items-center z-10 rounded-b-[1.875rem] overflow-hidden rounded pb-3">
        <div className="bg-white w-[96%] min-h-[90vh] flex flex-col rounded-b-[1.875rem] mx-auto">
          <div className="xs:pt-[5rem] pt-[1rem] flex lg:justify-center lg:mr-[20%] ml-[5%] lg:ml-0">
            <LogoAE />
          </div>
          {page === 1 ? <div>{news[0].link}</div> : null}
          <PaginationNews
            buttonClicked={buttonClicked}
            setButtonClicked={setButtonClicked}
            hidden={page === 1}
          />
          <div hidden={buttonClicked[2] || page === 1}>
            <HeroNews />
          </div>
          <div
            className="overflow-y-auto h-[50%]"
            hidden={buttonClicked[2] || page === 1}
          >
            <div className="pt-[1.5rem]">
              <CardNews
                text={handleClick().text}
                key={handleClick().key}
                image={handleClick().image}
                link={arrowPage}
              />
            </div>
            <div className="pt-[1rem]">
              <CardNews
                text={buttonClicked[1] ? news[7].text : news[1].text}
                key={buttonClicked[1] ? news[7].id : news[2].id}
                image={buttonClicked[1] ? news[7].image : news[1].image}
                link={arrowPage}
              />
            </div>
            <div className="pt-[1rem]" hidden={buttonClicked[1] ? true : false}>
              <CardNews
                text={buttonClicked[0] ? news[5].text : news[2].text}
                key={buttonClicked[0] ? news[5].id : news[3].id}
                image={buttonClicked[0] ? news[5].image : news[2].image}
                link={arrowPage}
              />
            </div>
            <div className="pt-[1rem]" hidden={buttonClicked[1] ? true : false}>
              <CardNews
                text={news[3].text}
                key={news[4].id}
                image={news[3].image}
                link={arrowPage}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="fixed bottom-[.56rem] left-[50%] translate-x-[-50%]">
        <FooterNavBar activePage={"news"} />
      </div>
    </div>
  );
};

export default News;
