package com.tourplanner.service;

import com.tourplanner.dto.AuthLoginRequestDto;
import com.tourplanner.dto.AuthRegisterRequestDto;
import com.tourplanner.dto.AuthResponseDto;
import com.tourplanner.exception.ServiceException;
import com.tourplanner.model.entity.UserEntity;
import com.tourplanner.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:authtest;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void registerStoresPassword() {
        AuthRegisterRequestDto request = new AuthRegisterRequestDto("test-user", "secret");

        AuthResponseDto response = authService.register(request);

        assertNotNull(response.userId());
        assertEquals("test-user", response.username());

        UserEntity savedUser = userRepository.findById(response.userId()).orElseThrow();
        assertEquals("secret", savedUser.getPasswordHash());
    }

    @Test
    void registerRejectsDuplicateUsername() {
        AuthRegisterRequestDto first = new AuthRegisterRequestDto("duplicate", "secret");
        AuthRegisterRequestDto second = new AuthRegisterRequestDto("duplicate", "another");

        authService.register(first);

        ServiceException ex = assertThrows(ServiceException.class, () -> authService.register(second));
        assertTrue(ex.getMessage().toLowerCase().contains("already"));
    }

    @Test
    void loginReturnsUserOnValidCredentials() {
        AuthRegisterRequestDto registerRequest = new AuthRegisterRequestDto("login-user", "secret");
        AuthResponseDto registered = authService.register(registerRequest);

        AuthLoginRequestDto loginRequest = new AuthLoginRequestDto("login-user", "secret");
        AuthResponseDto response = authService.login(loginRequest);

        assertEquals(registered.userId(), response.userId());
        assertEquals("login-user", response.username());
    }

    @Test
    void loginRejectsInvalidCredentials() {
        AuthRegisterRequestDto registerRequest = new AuthRegisterRequestDto("login-fail", "secret");
        authService.register(registerRequest);

        AuthLoginRequestDto loginRequest = new AuthLoginRequestDto("login-fail", "wrong");

        ServiceException ex = assertThrows(ServiceException.class, () -> authService.login(loginRequest));
        assertTrue(ex.getMessage().toLowerCase().contains("invalid"));
    }
}
