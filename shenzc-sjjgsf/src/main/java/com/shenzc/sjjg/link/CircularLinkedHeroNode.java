package com.shenzc.sjjg.link;

/**
 * @author shenzc
 * @create 2020-06-11-11:12
 */
public class CircularLinkedHeroNode {
    public static void main(String[] args) {
        CircularLinkedNode hero = new CircularLinkedNode();
        //hero.show();
        //hero.remove(new CircularHeroNode(3,"张飞"));
        //hero.show();
    }
}

class CircularLinkedNode{

    private CircularHeroNode head = null;

    //环形链表增加几个元素
    /*public boolean add(int num){
        //定义一个辅助变量
        CircularHeroNode temp = head;
        //遍历链表找到最后一个元素的next为空的
        while (true){
            if (temp.next == null){
                temp.next = circularHeroNode;
                circularHeroNode.next = head;
                return true;
            }
            temp = temp.next;
        }
    }*/



    //约瑟夫问题：一群孩子围成一个圈，从第k个人开始数，数到第m个人，那个人出队列，又开始从第m+1个人数，知道所有人都出队列
    public void josephu(){

    }

}

class CircularHeroNode{

    public int no;

    public String name;

    public CircularHeroNode next;

    public CircularHeroNode(int no,String name){
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.no+"__"+this.name;
    }
}
