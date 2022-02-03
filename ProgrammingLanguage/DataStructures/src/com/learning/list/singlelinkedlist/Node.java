package com.learning.list.singlelinkedlist;

public class Node {
     public int data = 0;
     public Node next = null;

     public Node(){
          this.data=0;
          this.next=null;
     }

     public void displayNode(){
          System.out.println("{" + this.data + "}");
     }

}