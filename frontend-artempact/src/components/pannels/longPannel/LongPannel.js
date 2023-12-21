import React from 'react';
import styles from "./longPannel.module.css";

const LongPannel = ({imageURL, title, text}) => {
  return (
    <div className={styles.container} style={{ backgroundImage: `url(${imageURL})` }}>
        <div className={styles.title}>{title}</div>
        <div className={styles.text}>{text}</div>
    </div>
  )
}

export default LongPannel