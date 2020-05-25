package pl.coderslab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.security.CustomAuthenticationSuccessHandler;
import pl.coderslab.serviceimpl.SpringDataAdminDetailsService;


@Order(2)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig(SpringDataAdminDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private SpringDataAdminDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin()
                .successHandler(successHandler)
                .failureUrl("/login?error=true");

        http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}