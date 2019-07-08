package InterthreadCommunicationpipedReaderpipedWriter;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
/**
 * https://howtodoinjava.com/java/multi-threading/inter-thread-communication-using-piped-streams-in-java/
 */
public class PipedCommunicationTest {
    public PipedCommunicationTest() {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        try {
            writer.connect(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread writerThread = new Thread(new PipeWriterThread(writer, "writer"));
        Thread readThread = new Thread(new PipeReaderThread(reader, "reader"));
        readThread.start();
        writerThread.start();
    }

    public static void main(String[] args) {
        new PipedCommunicationTest();
    }
}