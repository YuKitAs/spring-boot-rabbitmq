package rabbit.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut1")
@Configuration
public class RabbitConfig {
    private static final String QUEUE_NAME = "rabbit-queue";

    @Bean
    public Queue rabbitQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Profile("receiver")
    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }
}
