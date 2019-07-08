package InterthreadCommunicationpipedReaderpipedWriter;

import java.io.PipedWriter;
/**
 * https://howtodoinjava.com/java/multi-threading/inter-thread-communication-using-piped-streams-in-java/
 */
public class PipeWriterThread implements Runnable {
    private PipedWriter writer;
    private String name;

    public PipeWriterThread(PipedWriter writer, String name) {
        this.writer = writer;
        this.name = name;
    }

    public void run() {
        try {
            while (true) {
                // Write some data after every two seconds
                //System.out.println("Testing data written...n");
                writer.write("Testing data written... " +"\r\n");
                writer.flush();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(" PipeThread Exception: " + e);
        }
    }

}
