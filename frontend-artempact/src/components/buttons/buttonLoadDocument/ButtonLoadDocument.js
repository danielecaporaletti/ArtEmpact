import React from 'react';
import uploadImage from "../../../assets/emoji/Upload.svg";
import styles from './ButtonLoadDocument.module.css';

const ButtonLoadDocument = ({textButton, textUnderButton}) => {
  return (
    <div>
        <button className={styles.buttonLoad}><img className={styles.uploadEmoji} src={uploadImage} alt="upload here" />{textButton}</button>
        <div className={styles.textButton}>{textUnderButton}</div>
    </div>
  )
}

export default ButtonLoadDocument