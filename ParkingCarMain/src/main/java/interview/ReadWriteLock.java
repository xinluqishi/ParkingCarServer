package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shikeyue on 2017/6/11.
 */
public class ReadWriteLock {

    private int readers = 0;
    private int writers = 0;
    private int writeRequest = 0;

    private Map<Thread, Integer> readingThreads = new HashMap<>();

    /**
     * 读锁，可以反复读
     * @throws InterruptedException
     */
    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || writeRequest > 0) {
            wait();
        }
        readers ++;
    }

    /**
     * 可重入的读锁
     */
    public synchronized void repeateLockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }

        readingThreads.put(callingThread, (getAccessCount(callingThread) + 1));
    }

    private int getAccessCount(Thread callingThread) {
        //当前线程如果是第一次就返回0
        //不是第一次就从map中取出来代表的访问次数
        Integer accessCount = readingThreads.get(callingThread);
        if (accessCount == null) { return 0; }
        return accessCount.intValue();
    }

    private boolean canGrantReadAccess(Thread callingThread) {
        if (writers > 0) { return false; }
        if (isReader(callingThread)) { return true; }
        if (writeRequest > 0) { return false; }
        return true;
    }

    private boolean isReader(Thread callingThread) {
        return readingThreads.get(callingThread) != null;
    }


    public synchronized void unlockRead() {
        readers --;
        notifyAll();
    }

    public synchronized void priorityUnlockRead() {
        Thread callingThread = Thread.currentThread();
        int accessCount = getAccessCount(callingThread);
        if (accessCount == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, (accessCount - 1));
        }
        notifyAll();
    }

    /**
     * 写锁，不可以反复写
     * @throws InterruptedException
     */
    public synchronized void lockWrite() throws InterruptedException {
        writeRequest ++;

        while(readers > 0 || writers > 0) {
            wait();
        }

        writeRequest --;
        writers ++;
    }

    public synchronized void unlockWrite() {
        writers --;
        notifyAll();
    }



}
