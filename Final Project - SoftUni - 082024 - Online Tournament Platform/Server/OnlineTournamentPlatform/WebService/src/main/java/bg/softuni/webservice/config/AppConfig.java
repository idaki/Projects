package bg.softuni.webservice.config;

import bg.softuni.userservice.service.UserService;
import bg.softuni.webservice.InitUsers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class AppConfig {

    @Bean(name = "applicationEventMulticaster")
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
    @Bean
    public CommandLineRunner commandLineRunner(UserService userService) {
        return new InitUsers(userService);
    }
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {

                if(mappingContext.getSource() != null) {
                    LocalDate parse = LocalDate
                            .parse(mappingContext.getSource(),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    return parse;
                }
                return null;
            }
        });

        return modelMapper;
    }


}


