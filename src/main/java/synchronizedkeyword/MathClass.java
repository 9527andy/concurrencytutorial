package synchronizedkeyword;

/**
 *
 */
public class MathClass {
    void printNumber(int n) throws InterruptedException{
        synchronized (this){
            for (int i = 1; i<=n; i++){
                System.out.println(Thread.currentThread().getName()+ "::"+i);
                Thread.sleep(500);
            }
        }
    }

    synchronized void printNumber(long n)throws InterruptedException{
        for (int i = 1; i<=n; i++){
            System.out.println(Thread.currentThread().getName()+ "::"+i);
            Thread.sleep(500);
        }
    }
}
