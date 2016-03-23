package demo.controller;

import demo.model.Message;
import demo.service.CompletableFutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@RestController
public class CompletableFutureController extends AbstractController {

    @Autowired
    private CompletableFutureService completableFutureService;

    @RequestMapping("/completable-future-sync")
    public Message getSync(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) throws Exception {
        CompletableFuture<Message> future = completableFutureService.get(delay, "CompletableFuture sync", getId(), true);
        return future.get();
    }

    @RequestMapping("/completable-future-async-blocking")
    public DeferredResult<Message> getAsyncBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return getAsync(delay, "CompletableFuture async blocking", false);
    }

    @RequestMapping("/completable-future-async-nonblocking")
    public DeferredResult<Message> getAsyncNonBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return getAsync(delay, "CompletableFuture async nonblocking", false);
    }

    private DeferredResult<Message> getAsync(int delay, String payload, boolean blocking) {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        CompletableFuture<Message> future = completableFutureService.get(delay, payload, getId(), blocking);
        future.whenComplete((result, error) -> {
            if (error != null) {
                deferred.setErrorResult(error);
            } else {
                deferred.setResult(result);
            }
        });
        return deferred;
    }

}
