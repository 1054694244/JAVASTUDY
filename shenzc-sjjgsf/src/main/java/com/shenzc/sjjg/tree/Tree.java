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
    public static void preSort(Tree tree){
        //输出中间节点
        System.out.println(tree.name);
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
    public static void middleSort(Tree tree){
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
    public static void postSort(Tree tree){
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
    public boolean remove(int no){
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
    }

}
