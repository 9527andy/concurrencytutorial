package throttlingtasksubmission;

import java.util.concurrent.*;

/**
 * 当调用 execute() 方法添加一个任务时，线程池会做如下判断：
 * 如果正在运行的线程数量小于 corePoolSize，那么马上创建线程运行这个任务；
 * 如果正在运行的线程数量大于或等于 corePoolSize，那么将这个任务放入队列。
 * 如果这时候队列满了，而且正在运行的线程数量小于 maximumPoolSize，那么还是要创建线程运行这个任务；
 * 如果队列满了，而且正在运行的线程数量大于或等于 maximumPoolSize，那么线程池会抛出异常，告诉调用者“我不能再接受任务了”。
 * 当一个线程完成任务时，它会从队列中取下一个任务来执行。
 * 当一个线程无事可做，超过一定的时间（keepAliveTime）时，线程池会判断，如果当前运行的线程数大于 corePoolSize，那么这个线程就被停掉。
 * 所以线程池的所有任务完成后，它最终会收缩到 corePoolSize 的大小。
 * <p>
 * 这样的过程说明，并不是先加入任务就一定会先执行。假设队列大小为 10，corePoolSize 为 3，maximumPoolSize 为 6，
 * 那么当加入 20 个任务时，执行的顺序就是这样的：首先执行任务 1、2、3，然后任务 4~13 被放入队列。这时候队列满了，
 * 任务 14、15、16 会被马上执行，而任务 17~20 则会抛出异常。最终顺序是：1、2、3、14、15、16、4、5、6、7、8、9、10、11、12、13。
 */
public class DemoExecutor {
    public static void main(String[] args) {
        Integer threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(4);
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(2, 5, 5000, TimeUnit.MILLISECONDS, blockingQueue);
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("DemoTask Rejected : " + ((DemoTask) r).getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lets add another time : " + ((DemoTask) r).getName());
                executor.execute(r);
            }
        });
        // Let start all core threads initially
        //executor.prestartAllCoreThreads();
        while (true) {
            threadCounter++;
            // Adding threads one by one
            //System.out.println("Adding DemoTask : " + threadCounter);
            executor.execute(new DemoTask(threadCounter.toString()));
            if (threadCounter == 50)
                break;
        }
    }
}
