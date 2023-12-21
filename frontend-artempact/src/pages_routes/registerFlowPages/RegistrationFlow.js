import React, {useState, useEffect} from 'react';
import WhatIAm from './WhatIAm';
import FinalRegistrationBusiness from './FinalRegistrationBusiness';
import FinalRegistrationCreative from './FinalRegistrationCreative';
import BusinessFlowPages from '../businessFlowPages/BusinessFlowPages';
import CreativeFlowPages from '../creativeFlowPages/CreativeFlowPages';

const RegistrationFlow = () => {

    const [registrationDates, setRegistrationDate] = useState(
        {
            isCreative: undefined,
            telNumber: null,
            city: null,
        }
    );

    const [registrationCompleted, setRegistrationCompleted] = useState(false);

    const handleIsCreativeChange = (value) => {
        setRegistrationDate({ ...registrationDates, isCreative: value })
    };

    const handleRegistration = (phone, city) => {
        setRegistrationDate({ ...registrationDates, telNumber: phone,  city: city});
        setRegistrationCompleted(true);
    };

    useEffect(() => {
      console.log(registrationDates);
    }, [registrationDates]);

    return (
        <>
            { !registrationCompleted && registrationDates.isCreative === undefined && 
              <WhatIAm handleIsCreativeChange={handleIsCreativeChange} /> 
            }
            { !registrationCompleted && registrationDates.isCreative === true && 
              <FinalRegistrationCreative handleRegistration={handleRegistration}/> 
            }
            { !registrationCompleted && registrationDates.isCreative === false && 
              <FinalRegistrationBusiness handleRegistration={handleRegistration}/> 
            }
            { registrationCompleted && registrationDates.isCreative === true && 
              <CreativeFlowPages /> 
            }
            { registrationCompleted && registrationDates.isCreative === false && 
              <BusinessFlowPages /> 
            }
        </>
    )
}

export default RegistrationFlow;
