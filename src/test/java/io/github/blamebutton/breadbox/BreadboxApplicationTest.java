package io.github.blamebutton.breadbox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadboxApplicationTest {

    private static final Logger logger = LoggerFactory.getLogger(BreadboxApplicationTest.class);

    private String token;
    private BreadboxApplication app;

    @BeforeEach
    void setUp() {
        token = System.getenv("BREADBOX_TOKEN");
        if (app == null) {
            app = new BreadboxApplication(token);
        }
    }

    @Test
    void main() {
        assertEquals(app.getToken(), token, "Token did not equal.");
    }

    @Test
    void getCommand() {
    }

    @Test
    void getCommands() {
        logger.info("{}", app.getCommands());
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