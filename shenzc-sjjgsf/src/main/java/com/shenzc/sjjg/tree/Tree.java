package com.shenzc.sjjg.tree;

/**
 * @author shenzc
 * @create 2020-06-15-9:13
 */
public class Tree {

    public int no;

    public String name;

    public Tree left;

    public Tree right;

    public Tree(int no,String name){
        this.no = no;
        this.name = name;
    }

    //前序遍历：中左右
    public void preSort(Tree tree){
        //输出中间节点
        System.out.println(tree.no);
        //遍历左边节点
        if (tree.left!=null){
            preSort(tree.left);
        }
        //遍历右边节点
        if (tree.right!=null){
            preSort(tree.right);
        }
    }

    //中序遍历：左中右
    public void middleSort(Tree tree){
        //遍历左边节点
        if (tree.left!=null){
            middleSort(tree.left);
        }
        //输出中间节点
        System.out.println(tree.name);
        //遍历右边节点
        if (tree.right!=null){
            middleSort(tree.right);
        }
    }

    //后序遍历：左右中
    public void postSort(Tree tree){
        //遍历左边节点
        if (tree.left!=null){
            postSort(tree.left);
        }
        //遍历右边节点
        if (tree.right!=null){
            postSort(tree.right);
        }
        //输出中间节点
        System.out.println(tree.name);
    }

    //前序查询
    public Tree selectPre(int no){
        if (this==null){
            return null;
        }
        if (this.no == no){
            return this;
        }
        if (this.left!=null){
            Tree tree = this.left.selectPre(no);
            if (tree!=null){
                return tree;
            }
        }
        if (this.right!=null){
            Tree tree = this.right.selectPre(no);
            if (tree!=null){
                return tree;
            }
        }
        return null;
    }

    //中序查询
    public Tree selectMiddle(int no){
        if (this==null){
            return null;
        }
        if (this.left!=null){
            Tree tree = this.left.selectMiddle(no);
            if (tree!=null){
                return tree;
            }
        }
        if (this.no == no){
            return this;
        }
        if (this.right!=null){
            Tree tree = this.right.selectMiddle(no);
            if (tree!=null){
                return tree;
            }
        }
        return null;
    }

    //后序查询
    public Tree selectPost(int no){
        if (this==null){
            return null;
        }
        if (this.left!=null){
            Tree tree = this.left.selectPost(no);
            if (tree!=null){
                return tree;
            }
        }
        if (this.right!=null){
            Tree tree = this.right.selectPost(no);
            if (tree!=null){
                return tree;
            }
        }
        if (this.no == no){
            return this;
        }
        return null;
    }

    //删除（暂时直接删除该节点整个子树
    /*public boolean remove(int no){
        //单链表，不能直接判断该节点，这样无法删除该节点，只能判断该节点的左右节点，然后通过该节点删除左右节点
        if (this==null){
            return false;
        }
        if (this.no == no){
            this.no = 0;
            this.name = null;
            this.left = null;
            this.right = null;
        }
        if (this.left!=null && this.left.no == no){
            this.left = null;
            return true;
        }
        if (this.right!=null && this.right.no == no){
            this.right = null;
            return true;
        }
        if (this.left!=null){
            this.left.remove(no);
        }
        if (this.right!=null){
            this.right.remove(no);
        }
        return false;
    }*/


    //二叉排序树
    //添加
    public boolean add(Tree tree){
        if (tree == null){
            return false;
        }
        if (tree.no>this.no){
            if (this.right == null){
                this.right = tree;
            }else {
                this.right.add(tree);
            }
        }else {
            if (this.left == null){
                this.left = tree;
            }else {
                this.left.add(tree);
            }
        }
        return true;
    }
    //查询当前节点
    public Tree searchTarget(int no){
        if (this.no == no){
            return this;
        }
        if (this.no>no){
            if (this.left!=null){
                return this.left.searchTarget(no);
            }
        }
        if (this.no<no){
            if (this.right!=null){
                return this.right.searchTarget(no);
            }
        }
        return null;
    }
    //查询当前节点父节点
    public Tree searchParent(int no){
        if ((this.left!=null && this.left.no == no) || (this.right!=null && this.right.no == no)){
            return this;
        }
        if (this.left!=null && this.no>no){
            return this.left.searchParent(no);
        }
        if (this.right!=null && this.no>no){
            return this.right.searchParent(no);
        }
        return null;
    }
    //查询当前树最小的节点
    public Tree deleteSearchMinTree(Tree tree){
        if (tree == null){
            return null;
        }
        Tree temp = tree.right;
        while (temp.left!=null){
            temp = temp.left;
        }
        remove(temp.no);
        return temp;
    }
    //删除
    public boolean remove(int no){
        if (this == null){
            return false;
        }
        //查找到当前要删掉的节点
        Tree target = searchTarget(no);
        Tree parent = searchParent(no);
        //如果没有找到当前节点
        if (target==null){
            return false;
        }
        //如果叶子节点
        if (target.right == null && target.left == null){
            if (parent!=null){
                if (parent.left == target){
                    parent.left = null;
                }
                if (parent.right == target){
                    parent.right = null;
                }
            }else {
                this.no = 0;
            }
            return true;
        }
        //如果是有两个子节点
        if (target.right != null && target.left != null){
            if (parent != null){
                //删除当前节点的右子树的最小节点，并返回最小节点
                Tree minTree = deleteSearchMinTree(target);
                //查询最小节点的父节点
                if (parent.right == target){
                    minTree.right = target.right;
                    minTree.left = target.left;
                    parent.right = minTree;
                }else if (parent.left == target){
                    minTree.right = target.right;
                    minTree.left = target.left;
                    parent.left = minTree;
                }
            }else {
                this.no = target.right.no;
                this.right = target.right.right;
                this.left = target.left;
            }
            return true;
        }
        //如果是只有一子节点
        if (target.right == null || target.left == null){
            if (parent != null){
                if (parent.right == target){
                    if (target.right != null){
                        parent.right = target.right;
                    }else if (target.left != null){
                        parent.right = target.left;
                    }
                }
            }else {
                if (target.left != null){
                    this.no = target.left.no;
                    this.left = target.left.left;
                    this.right = target.right;
                }else if (target.right != null){
                    this.no = target.right.no;
                    this.left = target.left;
                    this.right = target.right.right;
                }
            }
            if (parent != null){
                if (parent.left == target){
                    if (target.right != null){
                        parent.left = target.right;
                    }else if (target.left != null){
                        parent.left = target.left;
                    }
                }
            }else {
                this.no = target.no;
                this.left = target.left;
                this.right = target.right;
            }
        }
        return true;
    }
    //遍历
    public void preShow(){
        if (this == null){
            return;
        }
        if (this.left != null){
            this.left.preShow();
        }
        System.out.println(this.no);
        if (this.right != null){
            this.right.preShow();
        }
    }

    //平衡树
    //添加
    public boolean AVLadd(Tree tree){
        if (tree == null){
            return false;
        }
        if (tree.no>this.no){
            if (this.right == null){
                this.right = tree;
            }else {
                this.right.add(tree);
            }
        }else {
            if (this.left == null){
                this.left = tree;
            }else {
                this.left.add(tree);
            }
        }
        if (this.left.height() > this.right.height()){
            if (this.left.right.height()>this.left.left.height()){
                this.left.left();
            }else {
                this.right();
            }
        }
        if (this.right.height() > this.left.height()){
            if (this.right.left.height()>this.right.right.height()){
                this.right.right();
            }else {
                this.left();
            }
        }
        return true;
    }

    //左旋转
    public Tree left(){
        //创建一个新节点 = 当前根节点
        Tree newTree = this;
        newTree.left = this.left;
        newTree.right = this.right.left;
        this.no = this.right.no;
        this.left = newTree;
        this.right = this.right.right;
        return this;
    }

    //左旋转
    public Tree right(){
        //创建一个新节点 = 当前根节点
        Tree newTree = this;
        newTree.left = this.left.right;
        newTree.right = this.right;
        this.no = this.left.no;
        this.right = newTree;
        this.left = this.left.left;
        return this;
    }

    //获取树的高度
    public int height(){
        int leftHeight = 0;
        int rightHeight = 0;
        if (this == null){
            return 0;
        }
        if (this.left != null){
            leftHeight = height()+1;
        }
        if (this.right != null){
            rightHeight = height()+1;
        }
        return Math.max(leftHeight,rightHeight);
    }


}
