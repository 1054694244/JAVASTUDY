package com.shenzc.sjjg.tree;

import org.junit.Test;

/**
 * @author shenzc
 * @create 2020-06-15-9:30
 */
public class TreeTest1 {
    @Test
    public void test1(){
        Tree tree1 = new Tree(1,"刘一");
        Tree tree2 = new Tree(2,"关二");
        Tree tree3 = new Tree(3,"张三");
        Tree tree4 = new Tree(4,"赵四");
        Tree tree5 = new Tree(5,"黄五");
        tree3.left = tree1;
        tree1.right = tree2;
        tree3.right = tree4;
        tree4.right = tree5;
        //tree3.postSort(tree3);
        /*Tree tree = tree3.selectPost(5);
        if (tree!=null){
            System.out.println(tree.name);
        }*/
        tree3.remove(2);
        tree3.postSort(tree3);
    }
}
