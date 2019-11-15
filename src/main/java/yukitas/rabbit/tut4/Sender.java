package yukitas.rabbit.tut4;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    private int index;

    private int count;

    private final String[] keys = {"info", "error", "warning"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder();

        if (++this.index == 3) {
            this.index = 0;
        }

        String key = keys[this.index];
        builder.append(key.toUpperCase()).append(' ');
        builder.append((++this.count));
        String message = builder.toString();
        template.convertAndSend(direct.getName(), key, message);
        System.out.println("Sent message: " + message);
    }
}
