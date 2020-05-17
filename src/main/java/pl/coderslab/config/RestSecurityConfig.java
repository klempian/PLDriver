package pl.coderslab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.security.JWTAuthenticationFilter;
import pl.coderslab.security.JWTAuthorizationFilter;
import pl.coderslab.serviceimpl.SpringDataUserDetailsService;

import static pl.coderslab.security.SecurityConstants.SIGN_UP_URL;

@Order(1)
@Configuration
@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {


    public RestSecurityConfig(SpringDataUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private SpringDataUserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/advice/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/advice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/advice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/tag/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/tag/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/tag/**").hasRole("ADMIN")
                .antMatchers("/api/**").hasRole("USER")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService))
        ;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}