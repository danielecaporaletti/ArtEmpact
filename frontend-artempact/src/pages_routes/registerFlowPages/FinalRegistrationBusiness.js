import React, {useRef} from 'react';
import logoLong from '../../assets/logo/logo-long.svg';
import ButtonLoadDocument from '../../components/buttons/buttonLoadDocument/ButtonLoadDocument';
import InputRegistration from '../../components/inputRegistration/InputRegistration';
import ButtonContinueWhite from '../../components/buttons/buttonContinueWhite/ButtonContinueWhite';
import styles from './registrationFlow.module.css';

const FinalRegistrationBusiness = ({handleRegistration}) => {

    const inputValueCell = useRef('');
    const inputValueAddress = useRef('');

    const handleSubmit = () => {
        const cellValue = inputValueCell.current.value;
        const addressValue = inputValueAddress.current.value;

        console.log("Cellulare: ", cellValue);
        console.log("Cittá: ", addressValue);

        if (cellValue === "" || addressValue === "") {
            alert("Per favore, completa tutti i campi.");
            return;
        } else {
            handleRegistration(cellValue, addressValue);
        }
    }

    return (
        <div className={styles.containerRegister}>
            <div className={styles.containerLogo}>
                <img className={styles.imageLogo} src={logoLong} alt='ArtEmpact logo' />
            </div>
            <div className={styles.containerForInput}>
                <div className={styles.mainTitle}>Ci siamo quasi!</div>
                    <InputRegistration 
                        typeInput={"number"}
                        inputName={"Cellulare"} 
                        inputRef={inputValueCell} 
                    />
                    <InputRegistration 
                        typeInput={"text"}
                        inputName={"Cittá"} 
                        inputRef={inputValueAddress}
                    />
                    <ButtonLoadDocument 
                        textButton={"Carica la visura camerale della tua azienda in formato pdf"} 
                        textUnderButton={"Certifica il tuo profilo caricando la visura camerale per avere il badge. I creativi ti troveranno più facilmente. Una volta caricanto il documento riceverai la nostra risposta in 24-48 ore."}
                    />
                    <ButtonContinueWhite onClick={handleSubmit}/>
            </div>
        </div>
  )
}

export default FinalRegistrationBusiness