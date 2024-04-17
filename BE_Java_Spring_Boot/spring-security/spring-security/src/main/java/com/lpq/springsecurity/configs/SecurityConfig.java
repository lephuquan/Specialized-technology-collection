package com.lpq.springsecurity.configs;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpq.springsecurity.services.jwt.JwtTokenFilter;
import com.lpq.springsecurity.services.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl customUserDetailService;


    @Autowired
    private LogoutHandler logoutHandler;

    public SecurityConfig(
            UserDetailsServiceImpl customUserDetailService, LogoutHandler logoutHandler) {
        this.customUserDetailService = customUserDetailService;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/user/**").hasAnyAuthority("USER")
                        .requestMatchers("/api/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/public-account/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/api/public/logout"))
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }

//FOR SECURITY LESS THAN 6.0
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeRequests()
//                .antMatchers("/api/public/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/swagger-resources/**").permitAll()
//                .antMatchers("/api/management/**").hasAnyAuthority("SUPER_ADMIN")
//                .antMatchers("/api/admin/**").hasAnyAuthority("ADMIN","SUPER_ADMIN")
//                .anyRequest().authenticated()
//                .and().cors()
//                .and().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
//                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.authenticationProvider(authenticationProvider());
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.logout(
//                logout -> {
//                    logout.invalidateHttpSession(true)
//                            .clearAuthentication(true)
//                            .logoutRequestMatcher(new AntPathRequestMatcher("/api/public/logout"))
//                            .addLogoutHandler(logoutHandler)
//                            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
//                }
//        );
//
//        return http.build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public JwtTokenFilter authenticationJwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);

        return authenticationProvider;
    }
}
