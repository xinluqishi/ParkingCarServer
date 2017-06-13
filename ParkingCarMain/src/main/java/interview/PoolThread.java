package interview;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shikeyue on 2017/6/12.
 */
public class PoolThread {

    private ReentrantLock reentrantLock = new ReentrantLock();

    private void tryReentryLockMethod() throws InterruptedException {
        reentrantLock.tryLock();
        reentrantLock.lockInterruptibly();
    }

}
