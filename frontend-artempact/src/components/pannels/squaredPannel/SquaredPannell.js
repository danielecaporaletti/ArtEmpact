import React from 'react';
import styles from './squaredPannell.module.css';

const SquaredPannell = ({imageURL, title, text}) => {
  return (
    <div className={styles.container}>
      <div className={styles.buttonContainer} style={{ backgroundImage: `url(${imageURL})` }}>
        <div className={styles.title}>{title}</div>
        <div className={styles.text}>{text}</div>
      </div>
    </div>
  )
}

export default SquaredPannell