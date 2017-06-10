package interview;

/**
 * Created by shikeyue on 2017/6/5.
 */
public class MyThread implements Runnable {

    private ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "initialize thread local";
        }
    };

    public ThreadLocal<String> getThreadLocal() {
        return threadLocal;
    }

    public void setThreadLocal(ThreadLocal<String> threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {

    }

}
