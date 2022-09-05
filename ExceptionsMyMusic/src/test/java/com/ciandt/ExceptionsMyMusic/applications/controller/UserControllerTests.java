package com.ciandt.ExceptionsMyMusic.applications.controller;

import com.ciandt.ExceptionsMyMusic.application.client.MyFeignClient;
import com.ciandt.ExceptionsMyMusic.application.controllers.UserController;
import com.ciandt.ExceptionsMyMusic.domain.dto.Data;
import com.ciandt.ExceptionsMyMusic.domain.dto.TokenDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyFeignClient myFeignClient;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldGenerateToken() throws Exception {
        String token = "generatedToken";
        TokenDataDTO tokenDataDTO = new TokenDataDTO(new Data("devTest"));

        String jsonBody = objectMapper.writeValueAsString(tokenDataDTO);

        when(myFeignClient.clientUserName(tokenDataDTO))
                .thenReturn(token);

        ResultActions result =
                mockMvc.perform(post("/usuarios")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
