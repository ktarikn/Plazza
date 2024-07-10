package org.example.securityold;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = { //Anyone can enter
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/",
            "/swagger-resources/**",
            "/home",
            "/ofisEkle**",

    };
    private static final String[] CREATE = { //only admin, specified below in configure method
            "/rates/post**",
            "/rates/add**",
            "/rates/update**",
            "/rates/post**"
    };
    private static final String[] REMOVE = {//only admin, specified below in configure method
//            "/rates/remove**",
            "/ofisSil**",
            "/ofisGuncelle**"
    };


    @Bean
    public UserDetailsManager userDetailsManagerBean(DataSource dataSource) {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user); //TODO
        return users;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http

                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .requestMatchers(CREATE).hasRole("CUSTOMER")
                                .requestMatchers(REMOVE).hasRole("ADMIN")
                                .anyRequest().authenticated()
//                        .anyRequest().authenticated().
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        // @formatter:on
        return http.build();
    }

    /*public SecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }*/


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}

