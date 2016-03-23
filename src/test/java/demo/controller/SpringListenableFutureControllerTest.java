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
public class SpringListenableFutureControllerTest extends AbstractControllerTest {

    @Test
    public void testSync() throws Exception {
        testSync("/spring-listenable-future-sync?delay=10", "Spring ListenableFuture sync");
    }

    @Test
    public void testAsyncBlocking() throws Exception {
        testAsync("/spring-listenable-future-async-blocking?delay=10", "Spring ListenableFuture async blocking");
    }

    @Test
    public void testAsyncNonBlocking() throws Exception {
        testAsync("/spring-listenable-future-async-nonblocking?delay=10", "Spring ListenableFuture async nonblocking");
    }

}