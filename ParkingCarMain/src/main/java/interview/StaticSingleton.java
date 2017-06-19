package interview;

/**
 * Created by shikeyue on 2017/6/18.
 */
public class StaticSingleton {

    private StaticSingleton() {
        System.out.println("StaticSingleton contractor");
    }

    private static class SingletonHolder {

        private static StaticSingleton instance = new StaticSingleton();

//        static {
//            System.out.println("SingletonHolder contractor");
//        }
    }

    public static StaticSingleton getInstance() {
        System.out.println("get instance");
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        StaticSingleton.getInstance();
    }

}
