package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/addressbooks";
    }

    @Test
    public void testCreateAndGetAddressBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("{}", headers);
        ResponseEntity<AddressBook> createResponse =
                restTemplate.postForEntity(getBaseUrl(), request, AddressBook.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        AddressBook createdBook = createResponse.getBody();
        assertThat(createdBook).isNotNull();
        assertThat(createdBook.getBuddyList()).isEmpty();

        Long id = createdBook.getId();
        ResponseEntity<AddressBook> getResponse =
                restTemplate.getForEntity(getBaseUrl() + "/" + id, AddressBook.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getId()).isEqualTo(id);
    }

    @Test
    public void testAddBuddyToAddressBook() {
        ResponseEntity<AddressBook> createResponse =
                restTemplate.postForEntity(getBaseUrl(), new AddressBook(), AddressBook.class);
        AddressBook createdBook = createResponse.getBody();

        BuddyInfo buddy = new BuddyInfo("Alice", 905, "Oshawa", "16 King St.");

        ResponseEntity<BuddyInfo> buddyResponse = restTemplate.postForEntity(
                getBaseUrl() + "/" + createdBook.getId() + "/buddies",
                buddy,
                BuddyInfo.class
        );

        assertThat(buddyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(buddyResponse.getBody()).isNotNull();
        assertThat(buddyResponse.getBody().getName()).isEqualTo("Alice");
        assertThat(buddyResponse.getBody().getHome()).isEqualTo("Oshawa");
    }
}
