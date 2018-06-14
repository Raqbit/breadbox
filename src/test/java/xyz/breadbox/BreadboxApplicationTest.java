package xyz.breadbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadboxApplicationTest {

    @Test
    void main() {
        BreadboxApplication app = new BreadboxApplication("token");
        assertEquals(app.getToken(), "token", "Token did not equal.");
    }
}