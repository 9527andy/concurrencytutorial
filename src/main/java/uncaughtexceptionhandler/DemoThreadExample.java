package uncaughtexceptionhandler;

/**
 * https://howtodoinjava.com/java/multi-threading/how-to-restart-thread-using-uncaughtexceptionhandler/
 */
public class DemoThreadExample {

    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.start();
    }
}
