import React from 'react';
import styles from './buttoncategorySearch.module.css';

const ButtonCategorySearch = ({text}) => {
  return (
    <button className={styles.button}>{text}</button>
  )
}

export default ButtonCategorySearch