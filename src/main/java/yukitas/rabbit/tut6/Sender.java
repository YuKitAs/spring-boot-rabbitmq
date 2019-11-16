package yukitas.rabbit.tut6;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    private int dots = 0;
    private int count = 0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder();

        if (dots++ == 3) {
            dots = 1;
        }

        for (int i = 0; i < dots; i++) {
            builder.append('.');
        }

        builder.append((++count));
        String message = builder.toString();
        template.convertAndSend(queue.getName(), message);
        System.out.println("Sent message: " + message);
    }
}
