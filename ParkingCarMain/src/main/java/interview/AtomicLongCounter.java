package interview;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by shikeyue on 2017/6/12.
 */
public class AtomicLongCounter {

    private AtomicLong count = new AtomicLong(0);

    public void inc() {
        boolean updated = false;
        while (!updated) {
            long preCount = this.count.get();
            updated = this.count.compareAndSet(preCount, preCount + 1);
        }
    }

}
