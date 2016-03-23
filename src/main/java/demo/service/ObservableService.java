package demo.service;

import demo.model.Message;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ObservableService extends AbstractService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);

    public Observable<Message> get(int delay, String payload, Long id, boolean blocking) {
        if (blocking) {
            return Observable.<Message>create(s -> {
                delayBlocking(delay, payload, id);
                s.onNext(new Message(payload, id));
                s.onCompleted();
            }).subscribeOn(Schedulers.from(executorService));
        } else {
            return Observable.<Message>create(s -> {
                delayNonBlocking(delay, payload, id, () -> {
                    s.onNext(new Message(payload, id));
                    s.onCompleted();
                });
            });
        }
    }

}
