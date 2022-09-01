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

        Optional<User> list = userRepository.findUserByName(name);
        User entity = list.get();
        if (list.isEmpty()) {
            LOGGER.error("Usuário não existe na base de dados");
            throw new NoContentException("Usuário não existente");
        }

        LOGGER.info("Busca realizada com sucesso, usuário encontrado na base");
        return new UserDTO(entity);
    }
}
