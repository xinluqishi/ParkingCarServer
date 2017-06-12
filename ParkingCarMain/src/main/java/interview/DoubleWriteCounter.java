package interview;

/**
 * Created by shikeyue on 2017/6/12.
 */
public class DoubleWriteCounter {

    private volatile long countA = 0;
    private volatile long countB = 0;


    public void incA() {
        this.countA++;
    }

    public void incB() {
        this.countB++;
    }

    public long getCountA() {
        return this.countA;
    }

    public long getCountB() {
        return this.countB;
    }

}
