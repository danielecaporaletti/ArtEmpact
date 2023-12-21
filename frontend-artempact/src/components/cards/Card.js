import React, {useState} from 'react';
import styles from './card.module.css';
import shield from '../../assets/emoji/shiled.svg';
import menu from '../../assets/emoji/menuSmall.svg';
import arrowRight from '../../assets/emoji/arrowRight.svg';
import arrowLeft from '../../assets/emoji/arrowLeftsvg.svg';

const Card = ({database}) => {
    const images = [database.image1, database.image2, database.image3];
    const [currentImageIndex, setCurrentImageIndex] = useState(0);

    const goLeft = () => {
        setCurrentImageIndex(currentImageIndex === 0 ? images.length - 1 : currentImageIndex - 1);
    };

    const goRight = () => {
        setCurrentImageIndex(currentImageIndex === images.length - 1 ? 0 : currentImageIndex + 1);
    };

    return (
        <div className={styles.container} style={{ backgroundImage: `url(${images[currentImageIndex]})` }}>
            <button className={styles.arrowLeft} onClick={goLeft}><img src={arrowLeft} alt='arrow left' /></button>
            <button className={styles.arrowRight} onClick={goRight}><img src={arrowRight} alt='arrow right' /></button>
            <div className={`${styles.position1} ${currentImageIndex === 0 ? styles.active : ''}`}></div>
            <div className={`${styles.position2} ${currentImageIndex === 1 ? styles.active : ''}`}></div>
            <div className={`${styles.position3} ${currentImageIndex === 2 ? styles.active : ''}`}></div>
            <div className={styles.infoContainer}>
                <div>
                    <div className={styles.name}>{database.name}</div>
                    {database.verified? <img className={styles.badge} src={shield} alt="logo" /> : null}
                    <button className={styles.menu}><img className={styles.menuIMG} src={menu} alt='menu' /> </button>
                </div>
                <div className={styles.type}>{database.type}</div>
            </div>
        </div>
    )
}

export default Card