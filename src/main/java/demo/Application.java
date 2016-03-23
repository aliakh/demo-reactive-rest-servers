package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletCustomizer() {
        return new TomcatEmbeddedServletContainerCustomizer();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}