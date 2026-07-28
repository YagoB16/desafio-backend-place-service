package br.com.yagobarbosa.placeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import br.com.yagobarbosa.placeservice.domain.PlaceRepository;
import br.com.yagobarbosa.placeservice.domain.PlaceService;

@Configuration
@EnableR2dbcAuditing
public class PlaceConfig {

  @Bean
  PlaceService placeService(PlaceRepository placeRepository) {
    return new PlaceService(placeRepository);
  }
}
