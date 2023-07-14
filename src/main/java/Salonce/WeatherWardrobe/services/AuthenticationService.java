package Salonce.WeatherWardrobe.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService {

    public String getUserName(Authentication authentication){
        return Optional.of(authentication.getPrincipal())
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(OidcUser::getGivenName)
                .orElseGet(authentication::getName);
    }

    public String getUserEmail(Authentication authentication){
        return Optional.of(authentication.getPrincipal())
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(OidcUser::getEmail)
                .orElseGet(authentication::getName);
    }

    public String getUserId(Authentication authentication){
        return Optional.of(authentication.getPrincipal())
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(OidcUser::getSubject)
                .orElseGet(authentication::getName);
    }
}
