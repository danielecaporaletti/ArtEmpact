import React from 'react';
import styles from './ButtonContinueWhite.module.css';

const ButtonContinueWhite = ({onClick}) => {
    return (
        <div className={styles.containerButton}>
            <button onClick={onClick} className={styles.button}>Continua</button>
        </div>
    )
}

export default ButtonContinueWhite