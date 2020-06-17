package com.shenzc.sjjg.tree;

import org.junit.Test;

/**
 * @author shenzc
 * @create 2020-06-15-9:30
 */
public class TreeTest1 {
    Tree tree1 = new Tree(1,"刘一");
    Tree tree2 = new Tree(2,"关二");
    Tree tree3 = new Tree(3,"张三");
    Tree tree4 = new Tree(4,"赵四");
    Tree tree5 = new Tree(5,"黄五");
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

    @Test
    public void test2(){
        OrderBinaryTree orderBinaryTree = new OrderBinaryTree(new int[]{1,2,3,4,5,6,7});//1 2 4 5 3 67
        orderBinaryTree.preOrder();
    }

    @Test
    public void test3(){
        ClueBinaryTree tree1 = new ClueBinaryTree(1);
        ClueBinaryTree tree2 = new ClueBinaryTree(2);
        ClueBinaryTree tree3 = new ClueBinaryTree(3);
        ClueBinaryTree tree4 = new ClueBinaryTree(4);
        ClueBinaryTree tree5 = new ClueBinaryTree(5);
        ClueBinaryTree tree6 = new ClueBinaryTree(6);
        ClueBinaryTree tree7 = new ClueBinaryTree(7);
        tree1.left = tree2;
        tree1.right = tree3;
        tree2.left = tree4;
        tree2.right = tree5;
        tree3.left = tree6;
        tree3.right = tree7;
        tree1.clueBinaryTree();
        //System.out.println(tree4.left);
        //out.println(tree4.right);
        tree1.showClueBinaryTree();
    }

    @Test
    public void test4(){
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.add(tree4);
        binarySortTree.add(tree2);
        /*binarySortTree.add(tree1);
        binarySortTree.add(tree3);
        binarySortTree.add(tree5);*/
        binarySortTree.remove(4);
        binarySortTree.show();
    }


}
