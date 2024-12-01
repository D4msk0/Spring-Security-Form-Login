package org.jcourant.springsecurityformlogin.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void passwordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        String rawPassword = "password";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertThat(passwordEncoder).isNotNull();
        assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void userDetailsService() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        var userDetailsService = securityConfig.userDetailsService(passwordEncoder);

        assertThat(userDetailsService).isNotNull();

        // Validate that the "user" is correctly set up
        var user = userDetailsService.loadUserByUsername("user");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(passwordEncoder.matches("password", user.getPassword())).isTrue();
    }

    @Test
    void securityFilterChain() {
        try {
            var httpSecurity = securityConfig.securityFilterChain(null);
            assertThat(httpSecurity).isNotNull();
        } catch (Exception e) {
            fail("Exception thrown while creating SecurityFilterChain: " + e.getMessage());
        }
    }

    @Test
    void testLoginPageAccessible() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void testProtectedResourceRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    void testLoginWithValidCredentials() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
                        .user("user")
                        .password("password"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    void testLoginWithInvalidCredentials() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
                        .user("user")
                        .password("wrongpassword"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    void testLogoutFunctionality() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout"));
    }
}