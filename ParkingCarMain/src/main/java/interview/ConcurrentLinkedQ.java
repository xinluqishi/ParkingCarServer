package interview;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by shikeyue on 17/6/18.
 */
public class ConcurrentLinkedQ {

    private static class Node<E> {
        volatile E item;
        volatile Node<E> next;

        boolean casItem(E cmp, E val) {
//            return UNSAFE.compareAndSwapObject(this, itemoffset, cmp, val);
            return false;
        }



    }


    private ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();


}
