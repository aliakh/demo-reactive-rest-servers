package demo.service;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import demo.model.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class GuavaListenableFutureService extends AbstractService {

    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(THREADS_COUNT));

    public ListenableFuture<Message> get(int delay, String payload, Long id, boolean blocking) {
        if (blocking) {
            return executorService.submit(() -> {
                delayBlocking(delay, payload, id);
                return new Message(payload, id);
            });
        } else {
            SettableFuture<Message> future = SettableFuture.create();
            delayNonBlocking(delay, payload, id, () -> {
                future.set(new Message(payload, id));
            });
            return future;
        }
    }

}
