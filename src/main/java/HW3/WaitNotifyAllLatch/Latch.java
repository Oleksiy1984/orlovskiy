package HW3.WaitNotifyAllLatch;

/**
 * @author Alexey.
 */
public class Latch {
    private final Object synchObj = new Object();
    private int count;

    public int getCount() {
        return count;
    }

    public Latch(int noThreads) {
        synchronized (synchObj) {
            this.count = noThreads;
        }
    }
    public void awaitZero() throws InterruptedException {
        synchronized (synchObj) {
            while (count > 0) {
                synchObj.wait();
            }
        }
    }
    public void countDown() {
        synchronized (synchObj) {
            if (--count <= 0) {
                synchObj.notifyAll();
            }
        }
    }
}

