import React from 'react';
import styles from './buttonChooseYesOrNot.module.css';

const ButtonChooseYesOrNot = ({isEmojy, image}) => {
  if(isEmojy) {
    return (
      <button className={styles.buttonEmoji}>
          <img className={styles.imageEmoji} src={image} alt="icon" />
      </button>
    );
  } else {
    return (
      <button className={styles.buttonNotEmoji}>
          <img className={styles.imageNotEmoji} src={image} alt="icon" />
      </button>
    );
  }
}

export default ButtonChooseYesOrNot;
