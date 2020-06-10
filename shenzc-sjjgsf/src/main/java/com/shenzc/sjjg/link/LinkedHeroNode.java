package com.shenzc.sjjg.link;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author shenzc
 * @create 2020-06-10-11:46
 */
public class LinkedHeroNode {
    public static void main(String[] args) {
        LinkedNode hero = new LinkedNode();
        hero.add2(new HeroNode(1,"刘备"));
        hero.add2(new HeroNode(3,"张飞"));
        hero.add2(new HeroNode(5,"关羽"));
        LinkedNode hero1 = new LinkedNode();
        hero1.add2(new HeroNode(2,"李二"));
        hero1.add2(new HeroNode(4,"赵四"));
        hero1.add2(new HeroNode(6,"王六"));
        //hero.show();
        //hero.remove(gy);
        //hero.show();
        //System.out.println(hero.length());
        //System.out.println(hero.getLastAtK(1));
        //hero.reverse1();
        //hero.show();
        //hero.reverseShow();
        hero.addAll(hero1.head);
        hero.show();
        //hero1.show();
    }
}


class LinkedNode{
    //先创建一个头节点，不存放数据
    public HeroNode head = new HeroNode(0,"");

    //最链表末尾段添加一个hero
    public boolean add(HeroNode heroNode){
        //定义一个辅助变量
        HeroNode temp = head;
        //遍历链表找到最后一个元素的next为空的
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        return true;
    }

    //根据链表中的元素no来顺先添加hero
    public boolean add2(HeroNode heroNode){
        //定义一个辅助变量
        HeroNode temp = head;
        boolean flat = false;
        //遍历链表找到最后一个元素的next为空的
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){
                HeroNode temp1 = temp.next;
                temp.next = heroNode;
                heroNode.next = temp1;
                flat = true;
                break;
            }else if (temp.next.no == heroNode.no){
                flat = true;
                break;
            }
            temp = temp.next;
        }
        if (!flat){
            temp.next = heroNode;
        }
        return true;
    }

    //是否为空
    public boolean isEmpty(){
        return head.next == null;
    }

    //删除一个元素
    public boolean remove(HeroNode heroNode){
        if (!isEmpty()){
            //判断链表是否存在该元素
            HeroNode temp = head;
            while (true){
                if (temp.next == null){
                    break;
                }
                if (temp.next.name == heroNode.name){
                    temp.next = heroNode.next;
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    //单链表的反转
    public void reverse(){
        if (head.next != null || head.next.next!=null){
            //创建一个新的单链表
            HeroNode node = new HeroNode(0,"");
            HeroNode next = null;
            //遍历链表，将取出来的数据放入到新链表的最前端
            HeroNode cur = head;
            while (cur.next != null){
                cur = cur.next;
                next = cur;
                //将temp存入到新的单链表中
                cur.next = node.next;
                node.next  = cur;
                cur = next;
            }
        }
    }

    public void reverse1(){
        if (head.next != null || head.next.next!=null){
            //创建一个新的单链表
            HeroNode node = new HeroNode(0,"");
            HeroNode next = null;
            //遍历链表，将取出来的数据放入到新链表的最前端
            HeroNode cur = head.next;
            while (cur!=null){
                next = cur.next;
                //将temp存入到新的单链表中
                cur.next = node.next;
                node.next  = cur;
                cur = next;
            }
            head.next = node.next;
        }
    }

    //查询单链表中倒数第k个节点
    public HeroNode getLastAtK(int k){
        if (isEmpty()){
            return null;
        }
        int length = length();
        if (length<k){
            return null;
        }
        //代表是链表中的第几个元素
        int index = length - k +1;
        int count = 0;
        HeroNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            if (++count == index){
                return temp.next;
            }
            temp = temp.next;
        }
        return null;
    }

    //获取单链表的长度（节点的个数）
    public int length(){
        if (isEmpty()){
            return 0;
        }
        HeroNode temp = head;
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
            HeroNode temp = head;
            while (true){
                if (temp.next == null){
                    break;
                }
                temp = temp.next;
                System.out.println(temp.name);
            }
        }
    }

    //倒序遍历
    public void reverseShow(){
        //将链表压入栈中
        Stack<HeroNode> stack = new Stack();
        HeroNode next = head.next;
        while (next!=null){
            stack.push(next);
            next = next.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //合并两个有序的单链表，合并完成之后仍然有序
    public void addAll(HeroNode heroNode){
        //遍历head链表
        HeroNode cur1 = head;
        HeroNode cur2 = heroNode.next;
        HeroNode next = null;
        while (cur2 != null){
            next = cur2.next;
            while (cur1.next != null){
                if (cur1.next.no > cur2.no){
                    cur2.next = cur1.next;
                    cur1.next = cur2;
                    break;
                }
                cur1 = cur1.next;
            }
            if (next == null && cur2 != null){
                HeroNode temp = head;
                while (true){
                    if (temp.next == null){
                        temp.next = cur2;
                        break;
                    }
                    temp =temp.next;
                }
            }
            cur2 = next;
        }
    }
}


class HeroNode {

    public int no;

    public String name;

    public HeroNode next;

    public HeroNode(int no,String name){
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return no+"-"+name;
    }
}
