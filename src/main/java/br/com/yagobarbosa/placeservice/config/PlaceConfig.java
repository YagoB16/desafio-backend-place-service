package br.com.yagobarbosa.placeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.yagobarbosa.placeservice.domain.PlaceRepository;
import br.com.yagobarbosa.placeservice.domain.PlaceService;

@Configuration
public class PlaceConfig {

  @Bean
  PlaceService placeService(PlaceRepository placeRepository) {
    return new PlaceService(placeRepository);
  }
}
