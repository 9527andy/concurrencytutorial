package synchronizedkeyword;


/**
 * https://howtodoinjava.com/java/multi-threading/java-synchronized/
 */
public class Main {
    public static void main(String[] args) {
        final MathClass mathClass = new MathClass();

        Runnable run = new Runnable() {
            public void run() {
                try {
                    //mathClass.printNumber(3);
                    mathClass.printNumber(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(run,"ONE").start();
        new Thread(run,"TWO").start();
    }
}
