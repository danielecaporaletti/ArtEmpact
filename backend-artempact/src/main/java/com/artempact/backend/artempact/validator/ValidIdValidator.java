package com.artempact.backend.artempact.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

public class ValidIdValidator implements ConstraintValidator<ValidId, Short> {

    @Autowired
    private ApplicationContext applicationContext;

    private String entityType;

    @Override
    public void initialize(ValidId constraintAnnotation) {
        this.entityType = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Short value, ConstraintValidatorContext context) {
        System.out.println("qua ci entro1" + value + entityType);
        if (value == null) {
            // Considera null come valido, o gestiscilo come preferisci.
            return true;
        }
        System.out.println("qua ci entro" + value + entityType);

        try {
            // Ottieni il bean del repository utilizzando il nome specificato nell'annotazione.
            JpaRepository repository = (JpaRepository) applicationContext.getBean(entityType + "Repository");


            // Usa existsById per verificare se l'entità esiste.
            return repository.existsById(value);
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("Il bean del repository non esiste, quindi l'ID non può essere valido.");
            // Il bean del repository non esiste, quindi l'ID non può essere valido.
            return false;
        }
    }

}

