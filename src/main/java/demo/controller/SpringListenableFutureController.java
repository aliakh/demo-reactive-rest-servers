package demo.controller;

import demo.model.Message;
import demo.service.SpringListenableFutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class SpringListenableFutureController extends AbstractController {

    @Autowired
    private SpringListenableFutureService springListenableFutureService;

    @RequestMapping("/spring-listenable-future-sync")
    public Message getSync(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) throws Exception {
        ListenableFuture<Message> future = springListenableFutureService.get(delay, "Spring ListenableFuture sync", getId(), true);
        return future.get();
    }

    @RequestMapping("/spring-listenable-future-async-blocking")
    public DeferredResult<Message> getAsyncBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return getAsync(delay, "Spring ListenableFuture async blocking", true);
    }

    @RequestMapping("/spring-listenable-future-async-nonblocking")
    public DeferredResult<Message> getAsyncNonBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return getAsync(delay, "Spring ListenableFuture async nonblocking", false);
    }

    private DeferredResult<Message> getAsync(int delay, String payload, boolean blocking) {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        ListenableFuture<Message> future = springListenableFutureService.get(delay, payload, getId(), blocking);
        future.addCallback(
                new ListenableFutureCallback<Message>() {
                    @Override
                    public void onSuccess(Message result) {
                        deferred.setResult(result);
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        deferred.setErrorResult(error);
                    }
                }
        );
        return deferred;
    }

}
