package demo.controller;

import demo.model.Message;
import demo.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Timer;
import java.util.TimerTask;

@RestController
public class BasicController extends AbstractController {

    @Autowired
    private BasicService basicService;

    private Timer timer = new Timer();

    @RequestMapping("/basic-sync")
    public Message getSync(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        return basicService.get(delay, "Basic sync", getId());
    }

    @RequestMapping("/basic-async-nonblocking")
    public DeferredResult<Message> getAsyncNonBlocking(@RequestParam(value = "delay", required = false, defaultValue = "0") int delay) {
        DeferredResult<Message> deferred = new DeferredResult<Message>(DEFERRED_RESULT_TIMEOUT);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (deferred.isSetOrExpired()) {
                    throw new IllegalStateException();
                } else {
                    deferred.setResult(new Message("Basic async nonblocking", getId()));
                }
            }
        }, delay);

        return deferred;
    }

}
