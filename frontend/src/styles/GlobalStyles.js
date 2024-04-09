import { createGlobalStyle } from "styled-components";

const GlobalStyles = createGlobalStyle`
:root {
  &, &.light-mode {

    --color-primary-color:#623bff;
    --color-white:#FFF;
    --color-white:#FFF;
    --color-primary-color:#623bff;
    --color-extra:#ff910a;
   
    /* --color-grey-shadow:rgba(229,231,235, 0.4); */
    --color-grey-shadow:rgba(229,231,235, 0.2);
    --color-grey-200:#e5e7eb; //(229,231,235)
    --color-grey-500:#6b7280; //(107,114,128)
    --color-grey-arrow:rgba(255, 255, 255, 0.50);; //(107,114,128)

    --shadow-btn-vert: 0px 4px 3px rgb(97 112 142 / 40%);


  }

  &.dark-mode {
   
  }
  
 
  

}

*,
*::before,
*::after {
  box-sizing: border-box;
  padding: 0;
  margin: 0;

  /* Creating animations for dark mode */
  transition: background-color 0.3s, border 0.3s;
}

html {
  /* font-size: 62.5%; */
  overflow:auto; 
}

body {

  background-color: #eefeff;
  background: #eefeff;
  height: 100%;
  font-family: 'Jost', sans-serif;
  transition: color 0.3s, background-color 0.3s;
  line-height: 1.5;
  font-size: 1.6rem;
}

input,
button,
textarea,
select {
  font: inherit;
  color: inherit;
}

button {
  cursor: pointer;
}

*:disabled {
  cursor: not-allowed;
}

select:disabled,
input:disabled {
  background-color: var(--color-grey-200);
  color: var(--color-grey-500);
}

input:focus,
button:focus,
textarea:focus,
select:focus {
  outline: 2px solid var(--color-brand-600);
  outline-offset: -1px;
}


button:has(svg) {
  line-height: 0;
}

a {
  color: inherit;
  text-decoration: none;
}

ul {
  list-style: none;
}

p,
h1,
h2,
h3,
h4,
h5,
h6 {
  overflow-wrap: break-word;
  hyphens: auto;
}

img {
  max-width: 100%;

  /* For dark mode */
  filter: grayscale(var(--image-grayscale)) opacity(var(--image-opacity));
}

`;

export default GlobalStyles;
