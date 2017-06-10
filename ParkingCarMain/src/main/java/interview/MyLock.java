package interview;

/**
 * Created by shikeyue on 2017/6/9.
 */
public class MyLock {

    private boolean isLocked = false;

    private Thread lockingThread = null;

    /**
     * 加锁
     */
    public synchronized void lockIt() throws InterruptedException {

        while (isLocked) {
          wait();
        }

        isLocked = true;

        lockingThread = Thread.currentThread();

    }

    /**
     * 解锁
     */
    public synchronized void unLockIt() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }

        isLocked = false;
        lockingThread = null;
        notify();
    }

}
