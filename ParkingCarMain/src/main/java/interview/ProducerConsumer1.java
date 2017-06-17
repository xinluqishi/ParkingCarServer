package interview;

import java.util.LinkedList;

/**
 * Created by shikeyue on 2017/6/17.
 */
public class ProducerConsumer1 {

    private LinkedList<Object> shareHouse = new LinkedList<>();

    private static int MAX = 10;

    public ProducerConsumer1() {
    }

    public void startPC() {
        new Producer().start();
        new Consumer().start();
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (shareHouse) {
                    try {
                        while (shareHouse.size() == MAX) {
                            shareHouse.wait();
                        }

                        Object newObject = new Object();
                        if (shareHouse.add(newObject)) {
                            System.out.println("Producer put a new Object");
                            Thread.sleep((long) (Math.random() * 3000));
                            shareHouse.notify();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }


    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (shareHouse) {
                    try {

                        while (shareHouse.size() == 0) {
                            shareHouse.wait();
                        }

                        shareHouse.removeLast();
                        System.out.println("Consumer consumer a new Object");
                        Thread.sleep((long) (Math.random() * 3000));
                        shareHouse.notify();



                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer1 pc = new ProducerConsumer1();
        pc.startPC();
    }

}
