package yukitas.rabbit.tut5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Server {
    @RabbitListener(queues = "tut.rpc.requests")
    public int fibonacci(int n) {
        System.out.println("Received request for " + n);
        int result = fib(n);
        System.out.println("Returned: " + result);
        return result;
    }

    private int fib(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }
}
