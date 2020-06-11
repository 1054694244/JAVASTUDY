package com.shenzc.sjjg.link;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author shenzc
 * @create 2020-06-10-11:46
 */
public class DoubleLinkedHeroNode {
    public static void main(String[] args) {
        DoubleLinkedNode hero = new DoubleLinkedNode();
        hero.add2(new DoubleHeroNode(9,"刘备"));
        hero.add2(new DoubleHeroNode(3,"张飞"));
        hero.add2(new DoubleHeroNode(5,"关羽"));
        hero.show();
        hero.remove(new DoubleHeroNode(3,"张飞"));
        hero.show();
    }
}


class DoubleLinkedNode{
    //先创建一个头节点，不存放数据
    public DoubleHeroNode head = new DoubleHeroNode(0,"");

    //最链表末尾段添加一个hero
    public boolean add(DoubleHeroNode doubleHeroNode){
        //定义一个辅助变量
        DoubleHeroNode temp = head;
        //遍历链表找到最后一个元素的next为空的
        while (true){
            if (temp.next == null){
                temp.next = doubleHeroNode;
                doubleHeroNode.pre = temp;
                return true;
            }
            temp = temp.next;
        }
    }

    //根据链表中的元素no来顺先添加hero
    public boolean add2(DoubleHeroNode doubleHeroNode){
        //定义一个辅助变量
        DoubleHeroNode temp = head;
        boolean flat = false;
        //遍历链表找到最后一个元素的next为空的
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > doubleHeroNode.no){
                doubleHeroNode.next = temp.next;
                temp.next.pre = doubleHeroNode;
                temp.next = doubleHeroNode;
                doubleHeroNode.pre = temp;
                flat = true;
                break;
            }else if (temp.next.no == doubleHeroNode.no){
                flat = true;
                break;
            }
            temp = temp.next;
        }
        if (!flat){
            temp.next = doubleHeroNode;
            doubleHeroNode.pre = temp;
        }
        return true;
    }

    //是否为空
    public boolean isEmpty(){
        return head.next == null;
    }

    //删除一个元素
    public boolean remove(DoubleHeroNode doubleHeroNode){
        if (!isEmpty()){
            //判断链表是否存在该元素
            DoubleHeroNode temp = head;
            while (true){
                if (temp.next == null){
                    break;
                }
                if (temp.next.no == doubleHeroNode.no){
                    temp.next = temp.next.next;
                    temp.next.pre = temp;
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    //获取双链表的长度（节点的个数）
    public int length(){
        if (isEmpty()){
            return 0;
        }
        DoubleHeroNode temp = head;
        //计数
        int count = 0;
        while (true){
            if (temp.next == null){
                break;
            }
            count++;
            temp = temp.next;
        }
        return count;
    }

    //遍历
    public void show(){
        if (!isEmpty()){
            DoubleHeroNode temp = head;
            while (true){
                if (temp.next == null){
                    break;
                }
                temp = temp.next;
                System.out.println(temp);
            }
        }
    }

}


class DoubleHeroNode {

    public int no;

    public String name;

    public DoubleHeroNode next;

    public DoubleHeroNode pre;

    public DoubleHeroNode(int no,String name){
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return no+"-"+name;
    }
}
