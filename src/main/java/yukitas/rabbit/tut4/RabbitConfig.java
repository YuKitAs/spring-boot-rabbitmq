package yukitas.rabbit.tut4;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut4")
@Configuration
public class RabbitConfig {
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("direct.exchange");
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
        public Binding binding1a(DirectExchange directExchange, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(directExchange).with("error");
        }

        @Bean
        public Binding binding2a(DirectExchange directExchange, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(directExchange).with("info");
        }

        @Bean
        public Binding binding2b(DirectExchange directExchange, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(directExchange).with("error");
        }

        @Bean
        public Binding binding2c(DirectExchange directExchange, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(directExchange).with("warning");
        }

        @Bean
        public Receiver receiver() {
            return new Receiver();
        }
    }
}
