package interview;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by shikeyue on 2017/6/17.
 */
public class BlockingQueueProCon {

    private LinkedBlockingDeque<Object> queue = new LinkedBlockingDeque<>(10);

    public BlockingQueueProCon() {
    }

    public void startPC() {
        new Producer().start();
        new Consumer().start();
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Object o = new Object();
                    queue.put(o);
                    System.out.println("add 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Object o = queue.take();
                    System.out.println("take 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueProCon b = new BlockingQueueProCon();
        b.startPC();
    }
}
