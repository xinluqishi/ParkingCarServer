package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shikeyue on 2017/6/11.
 */
public class SlippedCondition {

    private boolean isLocked = true;

    public void lock() {
        synchronized (this) {
            while (isLocked) {
                try {
                    this.wait();
                } catch (InterruptedException e) {

                }
            }
        }

        synchronized (this) {
            isLocked = true;
        }
    }

    public synchronized void unlock() {
        isLocked = false;
        this.notify();
    }


    /**
     * 内部类
     */
    public class SlipFairLock {

        private boolean isLocked = false;
        private Thread lockingThread = null;
        private List waitingThreads = new ArrayList();

        public void lock() throws InterruptedException {
            FairLock.QueueObject queueObject = new FairLock.QueueObject();

            synchronized (this) {
                waitingThreads.add(queueObject);
            }

            boolean mustWait = true;

            while (mustWait) {
                synchronized (this) {
                    mustWait = isLocked || waitingThreads.get(0) != queueObject;
                    if (!mustWait) {
                        waitingThreads.remove(queueObject);
                        isLocked = true;
                        lockingThread = Thread.currentThread();
                        return;
                    }
                }

                synchronized (queueObject) {
                    if (mustWait) {
                        try {
                            queueObject.wait();
                        } catch (InterruptedException e) {
                            waitingThreads.remove(queueObject);
                            throw e;
                        }
                    }
                }
            }

//            synchronized (this) {
//                waitingThreads.remove(queueObject);
//                isLocked = true;
//                lockingThread = Thread.currentThread();
//            }
        }


    }

    private static String a = "a";
    private String ab = "ab";

    private void staMethod() {
        a = "ss";
    }







    public static void main(String[] args) {

        for (int i=0; i++ < 10; i++) {
            System.out.println("i : " + ++i);
        }

    }

















}
