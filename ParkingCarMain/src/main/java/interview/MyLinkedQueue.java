package interview;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by shikeyue on 17/6/13.
 */
public class MyLinkedQueue<E> {

    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private AtomicReference<Node<E>> head = new AtomicReference<>(new Node<>(null, null));

    private AtomicReference<Node<E>> tail = head;

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> residue = curTail.next.get();
            if (curTail == tail.get()) {
                if (residue == null) {
                    if (curTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                } else {
                    tail.compareAndSet(curTail, residue);
                }
            }
        }
    }



}
