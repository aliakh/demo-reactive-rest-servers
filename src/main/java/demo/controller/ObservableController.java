package demo.controller;

import demo.model.Message;
import demo.service.ObservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

@RestController
public class ObservableController extends AbstractController {

    @Autowired
    private ObservableService observableService;

    @RequestMapping("/observable-sync")
    public Message getSync(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        Observable<Message> observable = observableService.get(delay, "Observable sync", getId(), true);
        return observable.toBlocking().first();
    }

    @RequestMapping("/observable-async-blocking")
    public DeferredResult<Message> getAsyncBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return getAsync(delay, "Observable async blocking", true);
    }

    @RequestMapping("/observable-async-nonblocking")
    public DeferredResult<Message> getAsyncNonBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return getAsync(delay, "Observable async nonblocking", false);
    }

    private DeferredResult<Message> getAsync(int delay, String payload, boolean blocking) {
        Observable<Message> observable = observableService.get(delay, payload, getId(), blocking);
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        observable.subscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
    }

}
