package yukitas.rabbit.tut5;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Client {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    private int start = 0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        System.out.println(String.format("Requesting fib(%d)", start));
        try {

            System.out.println("Got: " + template.convertSendAndReceive(exchange.getName(), "rpc", start++));
        } catch (AmqpException e) {
            System.out.println("Cannot connect to server");
        }
    }
}
