package pl.coderslab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.security.CustomAuthenticationSuccessHandler;
import pl.coderslab.serviceimpl.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/advice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/advice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/advice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/tag/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/tag/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/tag/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable()
        ;
    }

}