package yukitas.rabbit.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "task-queue")
public class Receiver {
    private final int instance;

    Receiver(int instance) {
        this.instance = instance;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(String.format("instance[%d] Received message: %s", instance, in));
        doWork(in);
        watch.stop();
        System.out.println(String.format("instance[%d] Done in %1.1f s", instance, watch.getTotalTimeSeconds()));
    }

    // Simulate the processing time according to the number of dots
    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(500);
            }
        }
    }
}
