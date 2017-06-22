package interview;

/**
 * Created by shikeyue on 2017/6/20.
 */
public class Base {

    int x = 10;

    public Base() {
        this.printMethod();
        int x = 20;
    }

    private void printMethod() {
        System.out.println("Base.x:" + x);
    }

    public static void main(String[] args) {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");

        System.out.println((a==b) + "and" + (a==c));
    }
}
