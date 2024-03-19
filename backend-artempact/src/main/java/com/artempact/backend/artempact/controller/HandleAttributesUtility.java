package com.artempact.backend.artempact.controller;

import com.artempact.backend.artempact.geoService.AsyncGeoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.*;

@Component
public class HandleAttributesUtility {

    private final AsyncGeoService asyncGeoService;

    public HandleAttributesUtility(AsyncGeoService asyncGeoService) {
        this.asyncGeoService = asyncGeoService;
    }

    // Ottieni i nomi delle proprietà con valore null di un oggetto
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }

    // Copia le proprietà tra DTO e entità escludendo i campi nulli
    public <T> void copyNonNullProperties(T source, T target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Metodo generico per gestire gli attributi lookup da un repository
    public <T, R, ID> void handleAttributeWithRepository(Optional<ID> id, Consumer<R> setter, JpaRepository<R, ID> repository) {
        id.ifPresent(i -> repository.findById(i).ifPresent(setter));
    }

}
