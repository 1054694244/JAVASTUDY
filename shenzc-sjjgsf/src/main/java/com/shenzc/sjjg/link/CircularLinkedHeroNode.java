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
        hero.add(5);

        hero.josephu(1,2,5);
        //hero.show();
        //System.out.println(hero.length());
    }
}


class CircularLinkedNode{

    private CircularHeroNode head;

    //环形链表增加几个元素
    public boolean add(int nums){
        if (nums<1){
            return false;
        }
        //初始化头指针
        head = new CircularHeroNode(1);
        //定义一个辅助指针
        CircularHeroNode help = head;
        int count = 0;
        for (int i=1;i<=nums;i++){
            if (i == 1){
                head.next = head;
            }else {
                CircularHeroNode node = new CircularHeroNode(i);
                help.next = node;
                node.next = head;
                help = help.next;
            }
        }
        return true;
    }

    //获取环形链表得长度
    public int length(){
        if (head == null){
            return 0;
        }
        CircularHeroNode help = head;
        int count  = 0;
        while (true){
            if (help.next == head){
                count++;
                break;
            }
            count++;
            help = help.next;;
        }
        return count;
    }

    //遍历环形链表1
    public void show(){
        if (length()<1){
            return;
        }
        CircularHeroNode help = head;
        while (true){
            if (help.next == head){
                System.out.println(help.no);
                break;
            }
            System.out.println(help.no);
            help = help.next;;
        }
    }



    //约瑟夫问题：一群孩子围成一个圈，从第k个人开始数，数到第m个人，那个人出队列，又开始从第m+1个人数，知道所有人都出队列
    public void josephu(int k,int m,int nums){
        if (k<1 || k>nums || head==null ){
            return;
        }
        CircularHeroNode help = head;
        //将辅助指针指向first
        while (true){
            if (help.next == head){
                break;
            }
            help = help.next;
        }
        //从第k个人开始报数  == 将第k个人做为head
        for (int i=0;i<k-1;i++){
            help = help.next;
            head = head.next;
        }
        //直到所有人出队列
        while (true){
            if (help == head){
                break;
            }
            for (int i=0;i<m-1;i++){
                help = help.next;
                head = head.next;
            }
            System.out.println(head.no);
            head = head.next;
            help.next = head;
        }
        System.out.println(head.no);
    }

}

class CircularHeroNode{

    public int no;


    public CircularHeroNode next;

    public CircularHeroNode(int no){
        this.no = no;
    }

    @Override
    public String toString() {
        return this.no+"__";
    }
}
