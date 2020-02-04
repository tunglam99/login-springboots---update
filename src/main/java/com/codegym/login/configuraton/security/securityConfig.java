package com.codegym.login.configuraton.security;

import com.codegym.login.configuraton.customeConfig.CustomAccessDeniedHandler;
import com.codegym.login.configuraton.customeConfig.RestAuthenticationEntryPoint;
import com.codegym.login.configuraton.filter.JwtAuthenticationFilter;
import com.codegym.login.model.VerificationToken;
import com.codegym.login.service.UserService;
import com.codegym.login.service.VerificationTokenService;
import com.codegym.login.service.impl.UserServiceImpl;
import com.codegym.login.service.impl.VerificationTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {
    public static final String LOGIN = "/login";

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Autowired
    private UserService userService;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)


    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
                .antMatchers("/",
                        LOGIN,
                        "/register",
                        "/new-password/**",
                        "/confirm-account/**",
                        "/users",
                        "/findAllQuestionByCategoryAndStatusIsTrue",
                        "/findAllQuestionByContentContainingAndStatusIsTrue",
                        "/findAllQuestionByTypeOfQuestion").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/categories",
                "                    /questions").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,
                        "/categories",
                                    "/questions").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT, "/categories",
                        "/typeOfQuestions",
                        "/questions",
                        "/answers",
                        "/quizzes").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/categories",
                        "/typeOfQuestions",
                        "/questions",
                        "/answers",
                        "/quizzes").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT, "/users")
                .access("hasRole('ROLE_USER')")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
