package interview;

import java.util.concurrent.locks.Lock;

/**
 * Created by shikeyue on 2017/6/18.
 */
public class DeadLockPractise implements Runnable{

    public boolean flag;

    static Object locka = new Object();
    static Object lockb = new Object();

    public DeadLockPractise(boolean flag) {
        this.flag = flag;
    }

    static class LockSam {
       static Object locka = new Object();
       static Object lockb = new Object();
    }

    @Override
    public void run() {
        while (true) {
            if (flag) {
                synchronized (locka) {
                    System.out.println("if locka");
                    synchronized (lockb) {
                        System.out.println("if lockb");
                    }
                }
            } else {
                synchronized (lockb) {
                    System.out.println("else lockb");
                    synchronized (locka) {
                        System.out.println("else locka");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockPractise(true));
        Thread t2 = new Thread(new DeadLockPractise(false));

        t1.start();
        t2.start();

        String str = "";
    }


}
