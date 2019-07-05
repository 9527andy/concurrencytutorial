package callablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class CallableExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();

        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            Integer integer = random.nextInt(10);
            FactorialCalculator factorialCalculator = new FactorialCalculator(integer);
            Future<Integer> future = executor.submit(factorialCalculator);
            futureList.add(future);
        }

        for (Future<Integer> future : futureList) {
            try {
                System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
