package demo.service;

import demo.model.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CompletableFutureService extends AbstractService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);

    public CompletableFuture<Message> get(int delay, String payload, Long id, boolean blocking) {
        if (blocking) {
            return CompletableFuture.supplyAsync(() -> {
                delayBlocking(delay, payload, id);
                return new Message(payload, id);
            }, executorService);
        } else {
            CompletableFuture<Message> future = new CompletableFuture<>();
            delayNonBlocking(delay, payload, id, () -> {
                future.complete(new Message(payload, id));
            });
            return future;
        }
    }

}
