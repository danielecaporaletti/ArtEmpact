/* eslint-disable import/no-anonymous-default-export */
/** @type {import('tailwindcss').Config} */
export default {
  content: ["./*.html", "./src/**/**/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      screens: {
        // tablet: "640px",
        // => @media (min-width: 640px) { ... }
        // laptop: "1024px",
        // => @media (min-width: 1024px) { ... }
        // desktop: "1280px",
        // => @media (min-width: 1280px) { ... }
      },
      colors: {
        "primary-color": "#623bff",
        "tertiary-color": "#ff910b",
        "pen-color": "#413A33",
        "pen-black": "#2F2F2F",
        "grey-shadow": "#e5e7eb33",
        "grey-arrow": "#ffffff80",
        "grey-dark": "#2F2F2F",

        "light-blue": "#37E7FF",
        blue: "#0f53b8",
        orange: "#ff910b",
        "light-violet": "#7a88f3",
        "grey-blue": "#99B8DD",

        grey: {
          100: "#f8f9fa",
          200: "#e5e7eb",
          300: "#D3D3D3",
          500: "#6b7280",
          700: "#656565",
        },
      },
      fontFamily: {
        montserrat: ["Montserrat", "sans-serif"],
        jost: ["Jost", "sans-serif"],
        roboto: ["Roboto", "sans-serif"],
      },

      boxShadow: {
        "btn-vert": "0px 4px 4px rgb(0 0 0 / 8%)",
        "3xl": "0px -4px 15px 0px rgba(98, 59, 255, 0.12)",
        "btn-rounded": "0px 4px 10px 0px rgba(0, 0, 0, 0.17)",

        "btn-colors": "0px 4px 13px rgba(0, 0, 0, 0.10)",

        "side-bar": "0px 1px 1px 1px rgba(0,0,0,1)",

        "textBox-shadow": "0px 4px 0px 0px rgba(0, 0, 0, 0.045)",

        "btn-creative-save-box-shadow":
          "0px -4px 15px 0px rgba(98, 59, 255, 0.12)",
      },

      dropShadow: {
        cardText: "0px 4px 4px rgba(0, 0, 0, 0.10)",
      },

      fontSize: {
        "card-title": "20px",
        "card-txt": "16px",
      },

      backgroundImage: {
        "gradient-rectangle-violet":
          "linear-gradient(164deg, #623BFF 1.35%, #623BFF 1.36%, #0f53b800 98.87%, #fff 98.88%)",
        "gradient-rectangle-sky":
          "linear-gradient(164deg,#99b8dd 54.52%,#fff 98.88%)",
        "gradient-layout":
          "linear-gradient(to right,#ede4f1, #bbb3f7, #bef2fb)",
        "gradient-purple-layout":
          "linear-gradient(164deg, #643DFF 14,94%, #FFF 83.68%)",
        "gradient-bg-arrow":
          "linear-gradient(164deg, #99B8DD 44.52%, #FFF 98.88%)",
        "gradient-research-history-lblue":
          "linear-gradient(151deg, #99B8DD 0%, #FFF 93.98%)",
        "gradient-research-history-violet":
          "linear-gradient(150deg, #623BFF 4.05%, #FFF 100%)",
        "gradient-result-work-discovery-violet":
          "linear-gradient(129deg, #623BFF 1.49%, #FFF 100%)",
      },

      plugins: [],
    },
  },
};
