package yukitas.rabbit.tut4;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class Receiver {
    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    @RabbitHandler
    public void receive(String in, int instance) throws InterruptedException {
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
