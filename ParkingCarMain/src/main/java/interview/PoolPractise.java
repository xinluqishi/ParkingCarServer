package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by shikeyue on 17/6/12.
 */
public class PoolPractise {

    private BlockingQueue taskQueue = null;

    private List<PoolThread> threads = new ArrayList<>();

    private boolean isStopped = false;

    public PoolPractise(int noOfThreads, int maxNoOfTasks) {
        taskQueue = new LinkedBlockingDeque(maxNoOfTasks);

        for (int i = 0; i < noOfThreads; i++) {
            threads.add(new PoolThread(taskQueue));
        }

        for (PoolThread thread: threads) {
            thread.start();
        }

    }


    public synchronized void executeIt(Runnable task) {
        if (this.isStopped) throw new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.add(task);
    }

    public synchronized void stopIt() {
        this.isStopped = true;
        for (PoolThread thread : threads) {
            thread.interrupt();
        }
    }


    /**
     * 内部类
     */
    private class PoolThread extends Thread {

        private BlockingQueue<Runnable> taskQueue = null;
        private boolean isStopped = false;

        public PoolThread(BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            while (!isStopped) {
                try {
                    Runnable runnable = taskQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void toStop() {
            isStopped = true;
            this.interrupt();
        }

        public synchronized boolean isStopped() {
            return isStopped;
        }

    }


    public static class MyLock {
        private AtomicBoolean locked = new AtomicBoolean(false);

        public boolean lock() {
            return locked.compareAndSet(false, true);
        }
    }


    public static void main(String[] args) {

    }

}
