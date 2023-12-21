import React from 'react';
import styles from './ButtonContinuePurple.module.css';

const ButtonContinueWhite = ({onClick, text}) => {
    return (
        <div className={styles.containerButton}>
            <button onClick={onClick} className={styles.button}>{text}</button>
        </div>
    )
}

export default ButtonContinueWhite