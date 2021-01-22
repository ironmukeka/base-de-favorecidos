package br.com.ironmukeka.favorecidos.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {


    @Bean
    public ModelMapper configurationModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
          modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.LOOSE);
          return modelMapper;
    }

}
