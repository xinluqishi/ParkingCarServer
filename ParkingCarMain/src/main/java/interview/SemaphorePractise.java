package interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shikeyue on 2017/6/11.
 */
public class SemaphorePractise {

    private boolean signal = false;
    /* 发出信号 打旗语 */
    Semaphore semaphore = new Semaphore(10);

    SendingThread sender = new SendingThread(semaphore);

    ReceivingThread receiver = new ReceivingThread(semaphore);

    /**
     * 代替notify()
     */
    public synchronized void take() {
        this.signal = true;
        this.notify();
    }

    /**
     * 代替 wait()
     * @throws InterruptedException
     */
    public synchronized void release() throws InterruptedException {
        while (!this.signal) {
            wait();
        }
        this.signal = false;
    }

    /**
     * 内部类
     */
    private class SendingThread {



        public SendingThread(Semaphore semaphore) {

        }
    }

    private class ReceivingThread {
        public ReceivingThread(Semaphore semaphore) {

        }
    }

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        lock1.tryLock();
        lock1.lockInterruptibly();
    }

}
