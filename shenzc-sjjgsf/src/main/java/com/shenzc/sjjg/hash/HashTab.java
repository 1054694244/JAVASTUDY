package com.shenzc.sjjg.hash;

/**
 * @author shenzc
 * @create 2020-06-12-16:31
 */
public class HashTab {

    private EmpLinkedList empLinkedList;

    private int[] empLinkedListArr;

}

class EmpLinkedList{

    public Emp head = null;

    public boolean add(Emp emp){
        if (head == null){
            head = emp;
        }
        Emp temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = emp;
        return true;
    }

    public Emp get(int no){
        if (head == null){
            return null;
        }
        Emp temp = head;
        while (true){
            if (temp.no == no){
                return temp;
            }
            if (temp.next == null){
                return null;
            }
            temp = temp.next;
        }
    }

    public boolean remove(int no){
        if (head == null){
            return false;
        }
        Emp temp = head;
        while (true){
            if (temp.no == no){
                temp = temp.next;
                return true;
            }
            if (temp.next == null){
                return false;
            }
            temp = temp.next;
        }
    }

}

//链表中的一个节点
class Emp{

    public int no;

    public String name;

    public Emp next;

    public Emp(int no,String name){
        this.no = no;
        this.name = name;
    }

}
