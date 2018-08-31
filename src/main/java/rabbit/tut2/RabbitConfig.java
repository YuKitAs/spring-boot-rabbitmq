package rabbit.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut2")
@Configuration
public class RabbitConfig {
    private static final String QUEUE_NAME = "task-queue";

    @Bean
    public Queue taskQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Profile("receiver")
    private static class ReceiverConfig {
        @Bean
        public Receiver receiver1() {
            return new Receiver(1);
        }

        @Bean
        public Receiver receiver2() {
            return new Receiver(2);
        }
    }
}
