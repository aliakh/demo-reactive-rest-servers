package demo.service;

import demo.model.Message;
import org.springframework.stereotype.Service;

@Service
public class BasicService extends AbstractService {

    public Message get(int delay, String payload, Long id) {
        delayBlocking(delay, payload, id);
        return new Message(payload, id);
    }

}
