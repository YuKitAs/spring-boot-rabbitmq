package rabbit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    @Bean
    public CommandLineRunner rabbit() {
        return new RabbitRunner();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
