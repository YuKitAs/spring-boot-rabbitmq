package yukitas.rabbit.tut5;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut5")
@Configuration
public class RabbitConfig {
    @Profile("sender")
    private static class ClientConfig {
        @Bean
        public DirectExchange exchange() {
            return new DirectExchange("tut.rpc");
        }

        @Bean
        public Client client() {
            return new Client();
        }
    }

    @Profile("receiver")
    private static class ServerConfig {
        @Bean
        public Queue queue() {
            return new Queue("tut.rpc.requests");
        }

        @Bean
        public DirectExchange exchange() {
            return new DirectExchange("tut.rpc");
        }

        @Bean
        public Binding binding(DirectExchange exchange, Queue queue) {
            return BindingBuilder.bind(queue).to(exchange).with("rpc");
        }

        @Bean
        public Server server() {
            return new Server();
        }
    }
}
