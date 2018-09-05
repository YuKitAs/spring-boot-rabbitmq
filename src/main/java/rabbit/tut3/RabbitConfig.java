package rabbit.tut3;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut3")
@Configuration
public class RabbitConfig {
    /**
     * Fanout exchange broadcasts all the messages it receives to all the queues it knows
     */
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("fanout.exchange");
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Profile("receiver")
    private static class ReceiverConfig {
        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanoutExchange, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanoutExchange);
        }

        @Bean
        public Binding binding2(FanoutExchange fanoutExchange, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanoutExchange);
        }

        @Bean
        public Receiver receiver() {
            return new Receiver();
        }

    }
}
