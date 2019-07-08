package InterthreadCommunicationpipedReaderpipedWriter;

import java.io.IOException;
import java.io.PipedReader;

/**
 * https://howtodoinjava.com/java/multi-threading/inter-thread-communication-using-piped-streams-in-java/
 */
public class PipeReaderThread implements Runnable {
    private PipedReader reader;
    private String name;

    public PipeReaderThread(PipedReader reader, String name) {
        this.reader = reader;
        this.name = name;
    }

    public void run() {
        while (true) {
            try {
                char ch = (char) reader.read();
                while (ch != -1) {
                    System.out.print(ch);
                    ch = (char) reader.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
