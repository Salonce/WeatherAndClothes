package Salonce.WeatherWardrobe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeConfig -> {
                    authorizeConfig.requestMatchers("/").permitAll();
                    authorizeConfig.requestMatchers("/error").permitAll();
                    authorizeConfig.requestMatchers("/home").permitAll();
                    authorizeConfig.requestMatchers("/weather").permitAll();
                    authorizeConfig.requestMatchers("/weather/coords").permitAll();
                    authorizeConfig.requestMatchers("/weather/city").permitAll();
                    authorizeConfig.requestMatchers("/register").permitAll();
                    authorizeConfig.requestMatchers("/authenticate").permitAll();
                    authorizeConfig.requestMatchers("/css/**").permitAll();
                    authorizeConfig.requestMatchers("/images/**").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())

                .build();
    }
/*
    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password("{bcrypt}$2a$10$X5wFBtLrL/kHcmrOGGTrGufsBX8CJ0WpQpF3pgeuxBB/H73BK1DW6")
                        .authorities("ROLE_user")
                        .build()
        );
    }
*/
}