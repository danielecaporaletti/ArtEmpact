import styles from '../../components/inputRegistration/inputRegistration.module.css';
import React from 'react'

const InputRegistration = ({ typeInput, inputName, inputRef }) => {
  return (
    <div className={styles.containerInput}>
        <div className={styles.inputTitle}>{inputName}</div>
        <input 
          type={typeInput} 
          ref={inputRef} 
          className={styles.input}
        />
    </div>
  )
}

export default InputRegistration
