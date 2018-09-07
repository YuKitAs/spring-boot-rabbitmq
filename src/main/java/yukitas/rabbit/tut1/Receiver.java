package yukitas.rabbit.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "rabbit-queue")
public class Receiver {
    @RabbitHandler
    public void receive(String message) {
        System.out.println("Received message: " + message);
    }
}
