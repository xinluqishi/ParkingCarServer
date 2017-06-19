package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shikeyue on 2017/6/18.
 */
public class TreeNode {

    TreeNode parent = null;

    List children = new ArrayList();

    public synchronized void addChild(TreeNode child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
            child.setParentOnly(this);
        }
    }

    public synchronized void setParentOnly(TreeNode parent) {
        this.parent = parent;
    }

    public synchronized void addChildOnly(TreeNode child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
        }
    }

    public synchronized void setParent(TreeNode parent) {
        this.parent = parent;
        parent.addChildOnly(this);
    }


    static TreeNode parentt = new TreeNode();
    static TreeNode child = new TreeNode();

    static Runnable run1 = new Runnable() {

        @Override
        public void run() {
            child.setParent(parentt);
            System.out.println("child done");
            parentt.addChild(child);
            System.out.println("parent done");
        }
    };

    static Runnable run2 = new Runnable() {
        @Override
        public void run() {
            parentt.addChild(child);
            System.out.println("parent done");
            child.setParent(parentt);
            System.out.println("child done");
        }
    };

    public static void main(String[] args) {

        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);

        t1.start();
        t2.start();

    }

}
