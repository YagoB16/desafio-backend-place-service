package br.com.yagobarbosa.placeservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yagobarbosa.placeservice.api.PlaceRequest;
import br.com.yagobarbosa.placeservice.api.PlaceResponse;
import br.com.yagobarbosa.placeservice.domain.PlaceService;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
public class PlaceController {
  private PlaceService placeService;

  public PlaceController(PlaceService placeService) {
    this.placeService = placeService;
  }

  @PostMapping
  public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request) {
    var placeResponse = placeService.create(request).map(PlaceMapper::fromPlaceToResponse);
    return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
  }

  @GetMapping
  public ResponseEntity<Flux<PlaceResponse>> listAll() {
    return ResponseEntity.ok(
        placeService.listAll().map(PlaceMapper::fromPlaceToResponse));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<PlaceResponse>> update(@PathVariable Long id, @Valid @RequestBody PlaceRequest request) {

    return placeService.update(id, request)
        .map(PlaceMapper::fromPlaceToResponse)
        .map(response -> ResponseEntity.ok(response))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
