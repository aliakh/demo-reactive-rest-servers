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
public class BasicControllerTest extends AbstractControllerTest {

    @Test
    public void testSync() throws Exception {
        testSync("/basic-sync?delay=10", "Basic sync");
    }

    @Test
    public void testAsync() throws Exception {
        testAsync("/basic-async-nonblocking?delay=10", "Basic async nonblocking");
    }

}