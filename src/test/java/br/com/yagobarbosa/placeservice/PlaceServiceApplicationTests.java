package br.com.yagobarbosa.placeservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.yagobarbosa.placeservice.api.PlaceRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlaceServiceApplicationTests {

  @LocalServerPort
  private int port;

  private WebTestClient webTestClient;

  @BeforeEach
  public void setUp() {
    this.webTestClient = WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port)
        .build();
  }

  @Test
  public void testCreatePlaceSucess() {
    var name = "Valid Name";
    var state = "Valid state";
    var slug = "valid-name";

    webTestClient
        .post()
        .uri("/places")
        .bodyValue(new PlaceRequest(name, state))
        .exchange()
        .expectStatus().isCreated()
        .expectBody()
        .jsonPath("$.name").isEqualTo(name)
        .jsonPath("$.state").isEqualTo(state)
        .jsonPath("$.slug").isEqualTo(slug)
        .jsonPath("$.createdAt").isNotEmpty()
        .jsonPath("$.updatedAt").isNotEmpty();
  }

  @Test
  public void testCreatePlaceFailure() {
    var name = "";
    var state = "";

    webTestClient
        .post()
        .uri("/places")
        .bodyValue(new PlaceRequest(name, state))
        .exchange()
        .expectStatus()
        .isBadRequest();
  }
}
