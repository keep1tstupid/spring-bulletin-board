package com.example.bb.ControllerTests;

import com.example.bb.payload.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private SecurityMockMvcRequestBuilders.FormLoginRequestBuilder makeLogin
            (String username, String password) {
        return formLogin()
                .user(username)
                .password(password);
    }

    @Test
    public void loginWithValidUserTest() throws Exception {
        this.mockMvc.perform(
                post("/api/auth/signin")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new LoginRequest("adm", "user"))))
                .andExpect(status().isOk());
    }

    @Test
    public void loginWithInvalidUserTest() throws Exception {
        this.mockMvc.perform(
                post("/api/auth/signin")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new LoginRequest("error", "error"))))
                .andExpect(unauthenticated());
    }
}
