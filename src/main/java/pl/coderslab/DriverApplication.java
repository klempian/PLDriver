package pl.coderslab;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.coderslab.facade.AdviceFacade;

@SpringBootApplication

public class DriverApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AdviceFacade adviceFacade(){
        return  new AdviceFacade();
    }

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }

}
