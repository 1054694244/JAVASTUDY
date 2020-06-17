package com.shenzc.sjjg.tree;

/**
 * @author shenzc
 * @create 2020-06-16-9:27
 */

/**
 * 顺序存储二叉树
 *  1：第n个元素的左子节点为2*n+1
 *  2: 第n个元素的右子节点为2*n+2
 *  3：第n个元素的付节点(n-1)/2
 *  4: n表示树中的第几个元素(n从0开始)
 */
public class OrderBinaryTree {

    private int[] arr;

    public OrderBinaryTree(int[] arr){
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    //前序遍历：给定一个数组（1，2，3，4，5，6，7）按照前序遍历
    public void preOrder(int index){
        if (arr == null){
            return;
        }
        System.out.println(arr[index]);
        if (arr.length>index*2+1){
            preOrder(index*2+1);
        }
        if (arr.length>index*2+2){
            preOrder(index*2+2);
        }
    }

}
