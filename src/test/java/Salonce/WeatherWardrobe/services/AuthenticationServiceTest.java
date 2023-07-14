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
        String expectedName = "Arthur Ant";
        OidcUser oidcUser = mock(OidcUser.class);
        when(oidcUser.getGivenName()).thenReturn(expectedName);

        Authentication authentication = new UsernamePasswordAuthenticationToken(oidcUser, null);

        //WHEN
        String userName = authenticationService.getUserName(authentication);

        //THEN
        assertEquals(expectedName, userName);
    }

    @Test
    void getUserEmail() {
        //GIVEN
        String expectedEmail = "email@gmail.com";
        OidcUser oidcUser = mock(OidcUser.class);
        when(oidcUser.getEmail()).thenReturn(expectedEmail);

        Authentication authentication = new UsernamePasswordAuthenticationToken(oidcUser, null);

        //WHEN
        String userEmail = authenticationService.getUserEmail(authentication);

        //THEN
        assertEquals(expectedEmail, userEmail);
    }

    @Test
    void getUserId() {
        //GIVEN
        String expectedId = "859701775623";
        OidcUser oidcUser = mock(OidcUser.class);
        when(oidcUser.getSubject()).thenReturn(expectedId);

        Authentication authentication = new UsernamePasswordAuthenticationToken(oidcUser, null);

        //WHEN
        String userId = authenticationService.getUserId(authentication);

        //THEN
        assertEquals(expectedId, userId);
    }

    @Test
    void testReturningAuthName(){
        //GIVEN
        String expectedName = "Arthur Ant";
        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(mock(Object.class));
        when(auth.getName()).thenReturn(expectedName);

        //WHEN
        String authName = authenticationService.getUserId(auth);

        //THEN
        assertEquals(authName, expectedName);
    }
}