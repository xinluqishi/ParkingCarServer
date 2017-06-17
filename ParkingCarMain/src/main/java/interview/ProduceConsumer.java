package interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shikeyue on 2017/6/17.
 */
public class ProduceConsumer {

    private LinkedList<Object> shareList = new LinkedList<>();

    private static final int MAX = 10;

    public ProduceConsumer() {
    }

    public void startPC() {

    }

    class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (shareList) {
                    try {
                        while (shareList.size() == MAX) {
                            shareList.wait();
                        }
                        Object newObject = new Object();
                        if (shareList.add(newObject)) {
                            Thread.sleep((long) (Math.random() * 3000));
                            shareList.notify();
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Producer is interrupted");
                    }

                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (shareList) {
                    try {

                        while (shareList.size() == 0) {
                            shareList.wait();
                        }

                        shareList.removeLast();
                        Thread.sleep((long) (Math.random() * 3000));
                        shareList.notify();

                    } catch (InterruptedException e) {
                        System.out.println("Consumer is interrupted");
                    }
                }
            }
        }
    }



    public static void main(String[] args) {
        System.out.println(Math.random() * 3000);
    }

}
