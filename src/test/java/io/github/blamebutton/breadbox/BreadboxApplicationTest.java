package io.github.blamebutton.breadbox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadboxApplicationTest {

    private String token;

    @BeforeEach
    void setUp() {
        token = System.getenv("BREADBOX_TOKEN");
    }

    @Test
    void main() {
        BreadboxApplication app = new BreadboxApplication(token);
        assertEquals(app.getToken(), token, "Token did not equal.");
    }

    @Test
    void getCommand() {
    }

    @Test
    void getCommands() {
    }

    @Test
    void getToken() {
    }

    @Test
    void registerCommand() {
    }

    @Test
    void getEnvironment() {
    }
}