package demo.service;

import demo.model.Message;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.Executors;

@Service
public class SpringListenableFutureService extends AbstractService {

    private final AsyncListenableTaskExecutor taskExecutor = new ConcurrentTaskExecutor(Executors.newFixedThreadPool(THREADS_COUNT));

    public ListenableFuture<Message> get(int delay, String payload, Long id, boolean blocking) {
        if (blocking) {
            return taskExecutor.submitListenable(() -> {
                delayBlocking(delay, payload, id);
                return new Message(payload, id);
            });
        } else {
            SettableListenableFuture<Message> future = new SettableListenableFuture<>();
            delayNonBlocking(delay, payload, id, () -> {
                future.set(new Message(payload, id));
            });
            return future;
        }
    }

}
