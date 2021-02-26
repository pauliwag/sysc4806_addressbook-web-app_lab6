package com.lab5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Paul Roode
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    private static final String ADD_BUDDY_FORM_TEXT = "Add to AddressBook ID #";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Verifies that the landing page content is initialized properly as per Runner::demo().
     */
    @Test
    public void shouldReturnAddBuddyFormText() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .contains(ADD_BUDDY_FORM_TEXT);
    }

}
