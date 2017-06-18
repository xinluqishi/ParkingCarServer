package interview;

import javafx.scene.chart.LineChart;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shikeyue on 2017/6/17.
 */
public class ProducerConsumerCondition {

    private ReentrantLock lock = new ReentrantLock();

    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    private LinkedList<Object> myList = new LinkedList<>();
    private int MAX = 10;


    public ProducerConsumerCondition() {
    }


    public void start1() {
        new Producer().start();
        new Consumer().start();
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();

                try {
                    while (myList.size() == MAX) {
                        full.await();
                    }

                    Object o = new Object();
                    if (myList.add(o)) {
                        System.out.println("Producer add a new object");
                        empty.signal();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (myList.size() == 0) {
                        empty.await();
                    }

                    myList.removeLast();
                    System.out.println("Consumer remove an Object");
                    full.signal();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) {
        ProducerConsumerCondition consumerCondition = new ProducerConsumerCondition();
        consumerCondition.start1();
    }


}
