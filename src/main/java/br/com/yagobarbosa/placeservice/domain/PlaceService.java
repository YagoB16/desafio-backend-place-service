package br.com.yagobarbosa.placeservice.domain;

import com.github.slugify.Slugify;

import br.com.yagobarbosa.placeservice.api.PlaceRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlaceService {
  private PlaceRepository placeRepository;
  private Slugify slg;

  public PlaceService(PlaceRepository placeRepository) {
    this.placeRepository = placeRepository;
    this.slg = Slugify.builder().build();
  }

  public Mono<Place> create(PlaceRequest placeRequest) {
    var place = new Place(null, placeRequest.name(), slg.slugify(placeRequest.name()), placeRequest.state(),
        null, null);
    return placeRepository.save(place);
  }

  public Flux<Place> listAll() {
    return placeRepository.findAll();
  }

  public Mono<Place> update(Long id, PlaceRequest request) {
    return placeRepository.findById(id)
        .flatMap(existingPlace -> {
          Place updatedPlace = new Place(
              existingPlace.id(),
              request.name(),
              slg.slugify(request.name()),
              request.state(),
              existingPlace.createdAt(),
              existingPlace.updatedAt()
          );

          return placeRepository.save(updatedPlace);
        });
  }
}
