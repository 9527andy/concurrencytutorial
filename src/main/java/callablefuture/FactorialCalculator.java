package callablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * https://howtodoinjava.com/java/multi-threading/java-callable-future-example/
 */
public class FactorialCalculator implements Callable {
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    public Object call() throws Exception {
        int result = 1;

        if (number == 0 || number == 1) result = 1;

        for (int i = 2; i <= number; i++) {
            result *= i;
            TimeUnit.MICROSECONDS.sleep(20);
        }
        System.out.println("Result for number - " + number + " -> " + result);
        return result;
    }
}
