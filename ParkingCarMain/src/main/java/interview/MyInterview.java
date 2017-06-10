package interview;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Fixing the issues about the interview of MIN SHENG bank
 * Created by shikeyue on 2017/6/3.
 */
public class MyInterview {

    public void handlePool() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 10, null, null);
        Executors.newFixedThreadPool(100);
    }


    /**
     * Producer and Consumer
     */
    static class ProducerConsumer {

        private LinkedList<Object> storeHouse = new LinkedList<>();
        private int MAX = 10;

        public ProducerConsumer() {
        }

        public void startProducerAndConsumer() {
            new Producer().start();
            new Consumer().start();
        }

        /**
         * inner class belongs to the Producer
         */
        class Producer extends Thread{

            @Override
            public void run() {
                // Producer keeps producing
                while (true) {
                    //lock the shared store

                    synchronized (storeHouse) {
                            try {

                                while (storeHouse.size() == MAX) {
                                    System.out.println("storeHouse is full, please wait");
                                    storeHouse.wait();
                                }

                                Object newObject = new Object();
                                if (storeHouse.add(newObject)) {
                                    System.out.println("Producer add a new Object to the storeHouse");
                                    Thread.sleep((long) Math.random() * 3000);
                                    storeHouse.notify();
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }

                }
            }
        }


        /**
         * inner class belongs to the Consumer
         */
        class Consumer extends Thread {
            @Override
            public void run() {
                while (true) {
                    synchronized (storeHouse) {
                        try {
                            while (storeHouse.size() == 0) {
                                System.out.println("storeHouse is empty, please wait");
                                storeHouse.wait();
                            }

                            storeHouse.removeLast();
                            System.out.println("Consumer consumes a object");
                            Thread.sleep((long) (Math.random() * 3000));
                            storeHouse.notify();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }

    }




    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        MyInterview.ProducerConsumer producerConsumer = new MyInterview.ProducerConsumer();
        producerConsumer.startProducerAndConsumer();
    }

}
