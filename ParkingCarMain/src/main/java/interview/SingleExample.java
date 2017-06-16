package interview;

import java.util.concurrent.Executor;

/**
 * Created by shikeyue on 17/6/16.
 */
public class SingleExample {

    private static volatile SingleExample instance;

    public static SingleExample getInstance() {
        if (instance == null) {
            synchronized (SingleExample.class) {
                if (instance == null) {
                    instance = new SingleExample();
                }
            }
        }
        return instance;
    }

    public void testExecute() {
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {

            }
        };


    }

}
