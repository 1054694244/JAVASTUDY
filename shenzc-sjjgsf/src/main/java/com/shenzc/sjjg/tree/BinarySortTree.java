package com.shenzc.sjjg.tree;

/**
 * @author shenzc
 * @create 2020-06-16-11:36
 */

/**
 * 二叉排序树，左节点小于当前节点，右节点大于当前节点，相同可以放到右节点，也可以放在左节点
 */
public class BinarySortTree {

    public Tree root;

    public boolean add(Tree tree){
        if (root == null){
            root = tree;
        }else {
            root.add(tree);
        }
        return true;
    }

    public void preOrder(){
        root.preSort(root);
    }

    public void remove(int no){
        root.remove(no);
    }

    public void show(){
        root.preShow();
    }
}
