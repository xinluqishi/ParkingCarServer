package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 线程池
 * Created by shikeyue on 2017/6/12.
 */
public class ThreadPoolPractise {

    private BlockingQueue taskQueue = null;

    private List<PoolThread> threads = new ArrayList<>();

    private boolean isStopped = false;

    public ThreadPoolPractise(int noOfThreads, int maxNoOfTasks) {
        taskQueue = new LinkedBlockingDeque();
    }

}
