package interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shikeyue on 2017/6/16.
 */
public class BoundedBuffer {

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[2]; //阻塞队列
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        System.out.println("进入put");
        lock.lock();
        System.out.println("put lock 锁住");
        try {
            while (count == items.length) {
                System.out.println("put notFull waiting");
                notFull.await();
            }
            items[putptr] = x;
        } catch (Exception e) {

        }
    }

}
