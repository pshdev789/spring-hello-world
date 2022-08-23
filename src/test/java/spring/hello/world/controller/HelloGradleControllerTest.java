package spring.hello.world.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class HelloGradleControllerTest {
    /** The qr controller. */
    @InjectMocks
    private HelloGradleController controller;
    @Test
    public void helloGradle() throws Exception {
//      assertEquals ("Hello Gradle!");
        assertEquals("Hello Gradle!",controller.helloGradle());
    }
}