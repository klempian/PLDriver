package pl.coderslab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.security.CustomAuthenticationSuccessHandler;
import pl.coderslab.security.JWTAuthenticationFilter;
import pl.coderslab.security.JWTAuthorizationFilter;
import pl.coderslab.service.impl.SpringDataAdminDetailsService;
import pl.coderslab.service.impl.SpringDataUserDetailsService;

import static pl.coderslab.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequiredArgsConstructor
    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private final SpringDataUserDetailsService userDetailsService;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .antMatcher("/api/**").authorizeRequests()
                    .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                    .antMatchers(HttpMethod.POST, "/api/advice/{advice_id}/training/").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/advice/").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/advice/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/advice/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/tag/").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/tag/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/tag/**").hasRole("ADMIN")
                    .antMatchers("/api/**").permitAll()
                    .and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService))
            ;
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }

        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        }
    }

    @RequiredArgsConstructor
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private final SpringDataAdminDetailsService adminDetailsService;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .antMatcher("/**")
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .and().formLogin()
                    .successHandler(new CustomAuthenticationSuccessHandler())
                    .failureUrl("/login?error=true");

            http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(adminDetailsService).passwordEncoder(bCryptPasswordEncoder);
        }
    }
}
