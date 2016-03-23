package demo.controller;

import demo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GuavaListenableFutureControllerTest extends AbstractControllerTest {

    @Test
    public void testSync() throws Exception {
        testSync("/guava-listenable-future-sync?delay=10", "Guava ListenableFuture sync");
    }

    @Test
    public void testAsyncBlocking() throws Exception {
        testAsync("/guava-listenable-future-async-blocking?delay=10", "Guava ListenableFuture async blocking");
    }

    @Test
    public void testAsyncNonBlocking() throws Exception {
        testAsync("/guava-listenable-future-async-nonblocking?delay=10", "Guava ListenableFuture async nonblocking");
    }

}