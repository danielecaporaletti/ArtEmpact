import React from 'react';
import styles from './squaredPanelWithEmoji.module.css';

const SquaredPanelWithEmoji = ({emojiURL, title}) => {
    return (
        <div className={styles.container}>
            <img className={styles.emoji} src={emojiURL} alt='emoji' />
            <div className={styles.title}>{title}</div>
        </div>
    )
}

export default SquaredPanelWithEmoji