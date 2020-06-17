package com.shenzc.sjjg.tree;

/**
 * @author shenzc
 * @create 2020-06-16-10:01
 */

/**
 * 线索化二叉树
 * 1：n个节点的二叉链表中含有n+1 【2n-(n-1)=n+1】个空指针域。利用二叉链表中的空指针域，
 * 存放指向该节点的在某种遍历次序下的前驱和后继节点的指针（之中附加的指针成为“线索”）
 * 2：一个节点的前一个节点，成为前驱节点。
 * 3：一个节点的后一个节点，称为后继节点
 */
public class ClueBinaryTree {

    public int no;

    public String name;

    public ClueBinaryTree left;

    public ClueBinaryTree right;

    public int leftType;

    public int rightType;

    public ClueBinaryTree pre;

    public ClueBinaryTree(int no){
        this.no = no;
    }

    public void clueBinaryTree(){
        clueBinaryTree(this);
    }

    //中序排序，线索化二叉树
    public void clueBinaryTree(ClueBinaryTree root){
        if (root==null){
            return;
        }
        clueBinaryTree(root.left);
        if (root.left == null){
            root.left = pre;
            root.leftType = 1;
        }
        if (pre!=null &&  pre.right==null){
            pre.right = root;
            pre.rightType = 1;
        }
        pre = root;
        clueBinaryTree(root.right);
    }

    //中序遍历，线索化二叉树
    public void showClueBinaryTree(){
        showClueBinaryTree(this);
    }

    //中序遍历，线索化二叉树
    public void showClueBinaryTree(ClueBinaryTree root){
        if (root == null){
            return;
        }
        if (root.left != null && root.leftType == 0){
            showClueBinaryTree(root.left);
        }
        System.out.println(root.no);
        if (root.right != null && root.rightType == 0){
            showClueBinaryTree(root.right);
        }
    }
}
