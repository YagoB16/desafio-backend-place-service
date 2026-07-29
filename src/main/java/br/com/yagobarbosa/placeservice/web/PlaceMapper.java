package br.com.yagobarbosa.placeservice.web;

import br.com.yagobarbosa.placeservice.api.PlaceResponse;
import br.com.yagobarbosa.placeservice.domain.Place;

public class PlaceMapper {
  public static PlaceResponse fromPlaceToResponse(Place place){
    return new PlaceResponse(place.id(), place.name(), place.slug(), place.state(),place.createdAt(), place.updatedAt());
  }
}
