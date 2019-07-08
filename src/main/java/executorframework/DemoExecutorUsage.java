package executorframework;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * https://howtodoinjava.com/java/multi-threading/executor-framework-tutorial/
 */
public class DemoExecutorUsage {
    private static volatile Future taskOneFuture;
    private static volatile Future taskTwoFuture;
    private static ExecutorService executor;

    public static void main(String[] args) {
        executor = Executors.newFixedThreadPool(2);

        while (true) {
            try {
                checkTasks();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Caught exception: " + e.getMessage());
            }
        }
    }

    private static void checkTasks() throws Exception {
        if (taskOneFuture == null || taskOneFuture.isDone() || taskOneFuture.isCancelled()) {
            taskOneFuture = executor.submit(new TaskOne());
        }

        if (taskTwoFuture == null || taskTwoFuture.isDone() || taskTwoFuture.isCancelled()) {
            taskTwoFuture = executor.submit(new TaskTwo());
        }
    }
}


class TaskOne implements Runnable {
    public void run() {
        while (true) {
            System.out.println("running task one");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class TaskTwo implements Runnable {
    public void run() {
        while (true) {
            System.out.println("running task two");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}