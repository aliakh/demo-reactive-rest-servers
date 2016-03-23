package demo.service;

import demo.util.Delay;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class DelayService {

    private Timer timer = new Timer();

    public void delayBlocking(int delay, String payload, Long id) {
        Delay.milliseconds(delay);
    }

    public void delayNonBlocking(int delay, String payload, Long id, Runnable runnable) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        }, delay);
    }

}
