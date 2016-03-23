package demo.controller;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import demo.model.Message;
import demo.service.GuavaListenableFutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class GuavaListenableFutureController extends AbstractController {

    @Autowired
    private GuavaListenableFutureService guavaListenableFutureService;

    @RequestMapping("/guava-listenable-future-sync")
    public Message getSync(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) throws Exception {
        ListenableFuture<Message> future = guavaListenableFutureService.get(delay, "Guava ListenableFuture sync", getId(), true);
        return future.get();
    }

    @RequestMapping("/guava-listenable-future-async-blocking")
    public DeferredResult<Message> getAsyncBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return getAsync(delay, "Guava ListenableFuture async blocking", true);
    }

    @RequestMapping("/guava-listenable-future-async-nonblocking")
    public DeferredResult<Message> getAsyncNonBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return getAsync(delay, "Guava ListenableFuture async nonblocking", false);
    }

    private DeferredResult<Message> getAsync(int delay, String payload, boolean blocking) {
        final DeferredResult<Message> deferred = new DeferredResult<>();
        final ListenableFuture<Message> future = guavaListenableFutureService.get(delay, payload, getId(), blocking);
        Futures.addCallback(future, new FutureCallback<Message>() {
            @Override
            public void onSuccess(Message result) {
                deferred.setResult(result);
            }

            @Override
            public void onFailure(Throwable error) {
                deferred.setErrorResult(error);
            }
        });
        return deferred;
    }

}
