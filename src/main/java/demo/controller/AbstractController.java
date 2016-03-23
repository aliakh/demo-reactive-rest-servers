package demo.controller;

import java.util.concurrent.atomic.AtomicLong;

abstract class AbstractController {

    protected static final Long DEFERRED_RESULT_TIMEOUT = null;

    private AtomicLong id = new AtomicLong(0);

    protected long getId() {
        return id.getAndIncrement();
    }

}
