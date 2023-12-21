import React from 'react';
import styles from "./searchCloselyButton.module.css";
import searchIcon from "../../../assets/emoji/search-closely.svg";

const SearchCloselyButton = () => {
  return (
    <button className={styles.button}>
        <img className={styles.image} src={searchIcon} alt='searcIcon' />
    </button>
  )
}

export default SearchCloselyButton