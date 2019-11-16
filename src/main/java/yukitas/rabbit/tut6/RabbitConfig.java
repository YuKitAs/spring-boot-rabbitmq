package yukitas.rabbit.tut6;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * RabbitMQ Delayed Message Plugin is required for this tut:
 * https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/
 */
@Profile("tut6")
@Configuration
public class RabbitConfig {
    private static final String ORIGINAL_QUEUE = "task-queue";
    private static final String DELAY_EXCHANGE = "delay.exchange";
    private static final String DLQ = "dead-letter-queue";
    private static final String DLX = "dead.letter.exchange";

    @Bean
    @Primary
    public Queue taskQueue() {
        Queue queue = new Queue(ORIGINAL_QUEUE);
        queue.getArguments().put("x-dead-letter-exchange", "");
        queue.getArguments().put("x-dead-letter-routing-key", DLQ);
        return queue;
    }

    @Bean
    @Qualifier("dead")
    public Queue deadLetterQueue() {
        return new Queue(DLQ);
    }

    @Bean
    @Qualifier("delayed")
    public DirectExchange delayExchange() {
        DirectExchange exchange = new DirectExchange(DELAY_EXCHANGE);
        exchange.setDelayed(true);
        return exchange;
    }

    @Bean
    @Qualifier("dead")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX);
    }

    @Bean
    public Binding bindOriginalToDelay() {
        return BindingBuilder.bind(taskQueue()).to(delayExchange()).with(ORIGINAL_QUEUE);
    }

    @Bean
    public Binding bindDeadLetter() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DLQ);
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Profile("receiver")
    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}
