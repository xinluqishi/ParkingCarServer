package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 读锁升级到可以同时获取写锁
 * Created by shikeyue on 2017/6/11.
 */
public class PriorityReadWriteLockUpdate {

    private Map<Thread, Integer> readingThreads = new HashMap<>();

    private int writeAccesses = 0;
    private int writeRequests = 0;

    private Thread writingThread = null;
    private int readers;


    public synchronized void lockWrite() throws InterruptedException {

        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }

        writeRequests --;
        writeAccesses ++;
        writingThread = callingThread;

    }

    public synchronized void unLockWrite() {
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    private boolean canGrantWriteAccess(Thread callingThread) {

        if (isOnlyReader(callingThread)) return true;

        if (hasReaders()) return false;

        if (writingThread == null) return true;

        if (!isWriter(callingThread)) return false;

        return true;
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readers == 1 && readingThreads.get(callingThread) != null;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    private boolean hasReaders() {
        return readingThreads.size() > 0;
    }


}
