import React from 'react';
import styles from './buttonRound.module.css';

const ButtonRound = ({image}) => {
  return (
    <button className={styles.button}>
        <img className={styles.image} src={image} alt="icon" />
    </button>
  )
}

export default ButtonRound