package pl.coderslab;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.facade.AdviceFacade;
import pl.coderslab.facade.TagFacade;
import pl.coderslab.serviceimpl.SpringDataUserDetailsService;

@SpringBootApplication

public class DriverApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public AdviceFacade adviceFacade(){
        return  new AdviceFacade();
    }

    @Bean
    public TagFacade tagFacade(){
        return  new TagFacade();
    }

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }

}
