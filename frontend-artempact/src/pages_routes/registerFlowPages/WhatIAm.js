import React from 'react';
import logoLong from '../../assets/logo/logo-long.svg';
import creative from '../../assets/creative-images/i-am-a-creative.svg';
import business from '../../assets/business-images/i-am-a-business.svg';
import styles from './registrationFlow.module.css';

const WhatIAm = ({handleIsCreativeChange}) => {

    return (
        <div className={styles.container}>  
            <div className={styles.logo}>
                <img src={logoLong} alt='logo artempact'/>
            </div>
            <div className={styles.title}>Un nuovo modo di fare impresa</div>
            <div className={styles.imageContainer}>
                <div className={styles.creativeCSS}>
                    <img 
                        onClick={() => handleIsCreativeChange(true)} 
                        className={styles.creativeIMG} 
                        src={creative} 
                        alt='I`m a creative'
                    />
                </div>
                <div className={styles.businessCSS}>
                    <img 
                        onClick={() => handleIsCreativeChange(false)} 
                        className={styles.businessIMG} 
                        src={business} 
                        alt='I`m a business'
                    />
                </div>
            </div>
            <div className={styles.footer}>
                <div className={styles.fotterTitle}>Scegli come vuoi iscriverti ad ArtEmpact.</div>
                <div className={styles.footerNoTitle}>Sei un creativo: mostra i tuoi lavori, amplia il tuo network e mettiti in contatto con Aziende che cercano un artista.</div>
                <div className={styles.footerNoTitle}>Sei un`azienda: presenta la tua vision e mission e trova il creativo più adatto alle tue esigenze per avere una nuova leva competitiva.</div>
            </div>
        </div>
    )
}

export default WhatIAm
