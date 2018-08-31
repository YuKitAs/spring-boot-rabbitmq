package rabbit.tut2;

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
        System.out.println("instance[" + instance + "] Received message: " + in);
        doWork(in);
        watch.stop();
        System.out.println("instance[" + instance + "] Done in " + watch.getTotalTimeSeconds() + "s");
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
