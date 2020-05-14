package pl.coderslab;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.coderslab.facade.AdviceFacade;
import pl.coderslab.facade.TagFacade;

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

    @Bean
    public TagFacade tagFacade(){
        return  new TagFacade();
    }

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }

}
