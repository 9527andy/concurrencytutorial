package objectlevellockvsclasslevellock;

/**
 * https://howtodoinjava.com/java/multi-threading/object-vs-class-level-locking/
 */
public class DemoClass1 {
    public void demoMethod() {
        //Acquire lock on .class reference
        synchronized (DemoClass.class) {
            //other thread safe code
        }
    }
}
