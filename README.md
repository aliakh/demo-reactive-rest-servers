# demo-reactive-rest-servers
Demo with reactive REST servers (Servlet 3.0 based asynchronous request processing)

Tools:
* application
  * Spring Boot
  * Gradle
* controllers
  * org.springframework.web.context.request.async.DeferredResult (Spring Web)
* services
  * java.util.concurrent.CompletableFuture (Java 8)
  * rx.Observable (RxJava)
  * org.springframework.util.concurrent.ListenableFuture (Spring Core)
  * com.google.common.util.concurrent.ListenableFuture (Google Guava)
* tests
  * JaCoCo
  * PIT
  * org.springframework.test.web.servlet.MockMvc (Spring Test)
  * Gatling

Docs:
* http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-ann-async
