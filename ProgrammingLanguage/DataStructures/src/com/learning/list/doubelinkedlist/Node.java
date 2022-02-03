package com.learning.list.doubelinkedlist;

public class Node {
    public int data;
    public Node next;
    public Node previous;

    public Node(){
        this.data = 0;
        this.next = null;
        this.previous = null;
    }

    public void displayNode(){
        System.out.println("{ " + this.data + " } ");
    }
}
