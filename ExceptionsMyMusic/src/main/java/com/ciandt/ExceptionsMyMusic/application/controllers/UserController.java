package com.ciandt.ExceptionsMyMusic.application.controllers;

import com.ciandt.ExceptionsMyMusic.application.client.MyFeignClient;
import com.ciandt.ExceptionsMyMusic.domain.dto.TokenDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private MyFeignClient myFeignClient;

    @PostMapping
    public String createUserNameClient(@RequestBody TokenDataDTO tokenDataDTO) {
        return myFeignClient.clientUserName(tokenDataDTO);
    }
}
