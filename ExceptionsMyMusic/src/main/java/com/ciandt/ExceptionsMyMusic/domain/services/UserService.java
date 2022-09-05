package com.ciandt.ExceptionsMyMusic.domain.services;

import com.ciandt.ExceptionsMyMusic.application.repositories.UserRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.UserDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.User;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.NoContentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findUserByName(String name) {
        Optional<User> optionalUser = userRepository.findUserByName(name);

        if (optionalUser.isEmpty()) {
            LOGGER.error("User not found on database");
            throw new NoContentException("Non existing user");
        }
        UserDTO userDTO = new UserDTO(optionalUser.get());

        LOGGER.info("Successfully search, user found in database");
        return userDTO;
    }
}
