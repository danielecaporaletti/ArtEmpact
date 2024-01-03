package com.artempact.backend.prova.service;

import com.artempact.backend.prova.dto.UserPatchDTO;
import com.artempact.backend.prova.entity.User;
import com.artempact.backend.prova.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User updateUserWithPatch(Long id, UserPatchDTO userUpdateDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        if (userUpdateDTO.getName() != null) {
            existingUser.setName(userUpdateDTO.getName());
        }
        if (userUpdateDTO.getAge() != null) {
            existingUser.setAge(userUpdateDTO.getAge());
        }

        return userRepository.save(existingUser);
    }
}
