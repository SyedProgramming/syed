package com.learning.list.singlelinkedlist;

import com.learning.list.circularlinkedlist.CircularLinkedList;


public class App {
    public static void main(String[] args) {
/*
        Node nodeA = new Node();
        nodeA.data = 1;

        Node nodeB = new Node();
        nodeA.data = 2;

        Node nodeC = new Node();
        nodeA.data = 3;

        Node nodeD = new Node();
        nodeA.data = 4;

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;

        System.out.println(listLength(nodeB));
        System.out.println(listLength(nodeC));
        System.out.println(listLength(nodeA));
*/


        //Single Linked List Call
        SingleLinkedList myList = new SingleLinkedList();
        myList.insertAtBeginning(100);
        myList.insertAtBeginning(200);
        myList.insertAtBeginning(300);
        myList.insertAtEnd(500);
        myList.displayResults();


    }

    public static int listLength(Node aNode) {
        int length=0;
        Node currentNode = aNode;
        while(currentNode.next != null){
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }



}
