package com.simplyrojgar.config;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.simplyrojgar.filter.JwtTokenFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(HttpMethod.GET, "/languages/**").permitAll()
                                 .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                 .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                                 .requestMatchers(HttpMethod.GET, "/api-docs/**").permitAll()
                                 .requestMatchers("/api/auth/**").permitAll()
                                 .anyRequest().authenticated()
                );
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.exceptionHandling()
      .authenticationEntryPoint(
          (request, response, ex) -> {
              response.sendError(
                  HttpServletResponse.SC_UNAUTHORIZED,
                  ex.getMessage()
              );
          }
      );

      http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
      /*
      	 * To allow all URLs use below code
      	 * http.authorizeRequests().anyRequest().permitAll();
      	 */
        return http.build();
    }
    
    
}
