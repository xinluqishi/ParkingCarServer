package interview;

/**
 * Created by shikeyue on 2017/6/13.
 */
public class Algorithm {

    /**
     * 打印两个有序链表的公共部分
     */
    public class Node {

        public int value;

        public Node next;

        public Node(int value) {
            this.value = value;
        }

        public void printCommonPart(Node head1, Node head2) {
            System.out.println("Common Part:");
            while (head1 != null && head2 != null) {
                if (head1.value < head2.value) {
                    head1 = head1.next;
                } else if (head1.value > head2.value) {
                    head2 = head2.next;
                } else {
                    System.out.println(head1.value + " ");
                    head1 = head1.next;
                    head2 = head2.next;
                }
            }
        }
    }

}
