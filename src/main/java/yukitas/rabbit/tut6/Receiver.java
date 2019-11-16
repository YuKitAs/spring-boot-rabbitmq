package yukitas.rabbit.tut6;

import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

public class Receiver {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Autowired
    @Qualifier("delayed")
    private DirectExchange exchange;

    @RabbitListener(queues = "task-queue")
    public void receive(Message in) {
        String msg = new String(in.getBody());
        if (msg.substring(0, msg.length() - 1).equals("...")) {
            sendToDelayed(in);
        } else {
            System.out.println(msg);
        }
    }

    private void sendToDelayed(Message delayedMessage) {
        Map<String, Object> headers = delayedMessage.getMessageProperties().getHeaders();
        // The message will be delivered to the original queue after x-delay milliseconds.
        headers.put("x-delay", 30000);

        // Set TTL on message. Expired messages (in the original queue) will be dead lettered
        if (Strings.isBlank(delayedMessage.getMessageProperties().getExpiration())) {
            delayedMessage.getMessageProperties().setExpiration("60000");
        }

        template.send(exchange.getName(), queue.getName(), delayedMessage);

        System.out.println(String.format("Sent message to delayed exchange: %s", delayedMessage));
    }
}
