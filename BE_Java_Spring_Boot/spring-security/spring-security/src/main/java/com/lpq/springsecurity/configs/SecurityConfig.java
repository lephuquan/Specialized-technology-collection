package com.lpq.springsecurity.configs;



import com.lpq.springsecurity.services.jwt.JwtTokenFilter;
import com.lpq.springsecurity.services.user.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl customUserDetailService; // Use final to enforce initialization in constructor - một implementaion của UserDetailsService được sử dụng để tải thông tin chi tiết người dùng từ cơ sở dữ liệu.

    private final ObjectMapper objectMapper;

    private final LogoutHandler logoutHandler;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {//  cấu hình các quy tắc bảo mật cho ứng dụng,  các quy tắc truy cập cho các URL khác nhau trong ứng dụng, cũng như cấu hình xác thực và phản hồi cho các tình huống không xác thực
        http.csrf(AbstractHttpConfigurer::disable)//  Vô hiệu hóa CSRF trong ứng dụng.
                .authorizeRequests()//  Xác định các quy tắc cho các URL cụ thể và quyền truy cập tương ứng.
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/user/**").hasAnyAuthority("ROLE_USER")
                .antMatchers("/api/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/admin-user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().authenticated()
                .and().cors()//Cấu hình CORS cho ứng dụng.
                .and().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))// Cấu hình điểm den cho việc xử lý các yêu cầu không được xác thực.
                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));// Cấu hình quản lý phiên không lưu trữ trạng thái trong ứng dụng.
        http.authenticationProvider(authenticationProvider());// cung cấp xác thực cho ứng dụng

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)//  được sử dụng để thêm một bộ lọc (authenticationJwtTokenFilter()) vào trước một bộ lọc hiện có, được thêm vào trước UsernamePasswordAuthenticationFilter, điều này đảm bảo rằng token JWT sẽ được xác thực trước khi xử lý xác thực dựa trên tên người dùng và mật khẩu.
                .logout()
                .logoutUrl("/api/public/logout") // catch logout request,  URL mà người dùng sẽ gửi yêu cầu đến để đăng xuất
                .addLogoutHandler(logoutHandler) // after process logout
                .logoutSuccessHandler((request, response, authentication) -> {// được sử dụng để thực hiện các hành động cụ thể SAU KHI quá trình đăng xuất hoàn thành.
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_OK);
                });

        return http.build();
    }

    @Bean
    public JwtTokenFilter authenticationJwtTokenFilter() {//  tạo và trả về một đối tượng JwtTokenFilter. Đây là một bean được sử dụng để xác thực token JWT trong quá trình xử lý yêu cầu.
        return new JwtTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {// Phương thức này tạo và trả về một đối tượng AuthenticationManager. Đối tượng này là một bean Spring Security quan trọng được sử dụng để quản lý quá trình xác thực của người dùng. Phương thức này nhận AuthenticationConfiguration làm tham số, cho phép truy cập vào các cấu hình xác thực
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {// tạo và trả về một đối tượng DaoAuthenticationProvider. Đối tượng này được sử dụng để cung cấp xác thực dựa trên thông tin người dùng từ cơ sở dữ liệu. Trong phương thức này
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService); // Set the UserDetailsService
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);

        return authenticationProvider;
    }
}
