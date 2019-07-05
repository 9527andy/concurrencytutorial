package objectlevellockvsclasslevellock;

/**
 * https://howtodoinjava.com/java/multi-threading/object-vs-class-level-locking/
 */
public class DemoObject1 {
    public void demoMethod() {
        synchronized (this) {
            //other thread safe code
        }
    }
}