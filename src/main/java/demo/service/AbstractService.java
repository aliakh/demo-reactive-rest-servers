package demo.service;

import org.springframework.beans.factory.annotation.Autowired;

abstract class AbstractService {

    protected static final int THREADS_COUNT = 100;

    @Autowired
    private DelayService delayService;

    public void delayBlocking(int delay, String payload, Long id) {
        delayService.delayBlocking(delay, payload, id);
    }

    public void delayNonBlocking(int delay, String payload, Long id, Runnable runnable) {
        delayService.delayNonBlocking(delay, payload, id, runnable);
    }

}
