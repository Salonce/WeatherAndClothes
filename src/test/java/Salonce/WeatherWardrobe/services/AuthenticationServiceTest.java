package Salonce.WeatherWardrobe.services;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService = new AuthenticationService();

    @Test
    void getUserName() {
        //GIVEN
        OidcUser oidcUser = mock(OidcUser.class);
        when(oidcUser.getGivenName()).thenReturn("some name");

        Authentication authentication = new UsernamePasswordAuthenticationToken(oidcUser, null);

        //WHEN
        String userName = authenticationService.getUserName(authentication);

        //THEN
        assertEquals("some name", userName);
    }

    @Test
    void getUserEmail() {
        //GIVEN
        OidcUser oidcUser = mock(OidcUser.class);
        when(oidcUser.getEmail()).thenReturn("some email");

        Authentication authentication = new UsernamePasswordAuthenticationToken(oidcUser, null);

        //WHEN
        String userEmail = authenticationService.getUserEmail(authentication);

        //THEN
        assertEquals("some email", userEmail);
    }

    @Test
    void getUserId() {
        //GIVEN
        final String SOME_ID = "some id";
        OidcUser oidcUser = mock(OidcUser.class);
        when(oidcUser.getSubject()).thenReturn(SOME_ID);

        Authentication authentication = new UsernamePasswordAuthenticationToken(oidcUser, null);

        //WHEN
        String userId = authenticationService.getUserId(authentication);

        //THEN
        assertEquals(SOME_ID, userId);

    }
}