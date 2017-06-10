package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * To understand the technique point related to the HashMap
 * Created by shikeyue on 2017/6/4.
 */
public class LearnHashMap {

    transient HashMap.Entry[] table;

    /**
     * Statements
     * 1<<30 = 1 073 741 824
     *
     * threshold = to resize (capacity * load factor)
     *
     * DEFAULT_INITIAL_CAPACITY = 16
     * DEFAULT_LOAD_FACTOR = 0.75f
     *
     *
     *
     *
     */

    /**
     * Computes key.hashCode() and spreads (XORs) higher bits of hash
     * to lower.  Because the table uses power-of-two masking, sets of
     * hashes that vary only in bits above the current mask will
     * always collide. (Among known examples are sets of Float keys
     * holding consecutive whole numbers in small tables.)  So we
     * apply a transform that spreads the impact of higher bits
     * downward. There is a tradeoff between speed, utility, and
     * quality of bit-spreading. Because many common sets of hashes
     * are already reasonably distributed (so don't benefit from
     * spreading), and because we use trees to handle large sets of
     * collisions in bins, we just XOR some shifted bits in the
     * cheapest possible way to reduce systematic lossage, as well as
     * to incorporate impact of the highest bits that would otherwise
     * never be used in index calculations because of table bounds.
     *
     * 计算hashCode和将高bit hash位散列到低位。
     * 由于table使用2的幂次模拟，哈希集合仅仅在高位bit变化所以总是造成冲突。
     *（普遍知道的例子就是浮点数key集合总是在一个量小的table中保持在连续的整数位波动）
     * 因此我们运用了一个移位的方式，它将高bit位向低位进行散列。
     * 这是一个针对位散列在速度，效率和质量上的权衡。
     * 因为很多的普通hash类集合已经被合理的分配（不要迷恋散列扩展），
     * 同时因为我们使用了树结构去处理在大存储空间中大集合的冲突，
     * 我们只是一些移位异或在可能的最便宜的方式来降低系统的lossage（XOR是异或）
     * （as well as to incorporate impact of the highest bits that would otherwise never be used in index calculations because of table bounds）
     * 以及一些表边界高位的极少被触及到使用索引的情况
     *
     * hash计算如下：
     * (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
     *
     *
     *
     */

    static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        int hash;
        Entry<K, V> next;

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }



    public static void main(String[] args) {
        String key = "sky";
        int h;
        System.out.println(h = key.hashCode());
        System.out.println(String.valueOf((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)));

        Map<String, Object> map = new HashMap<>(2^32);
    }


}
