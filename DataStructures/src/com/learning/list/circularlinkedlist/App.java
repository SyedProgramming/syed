package com.learning.list.circularlinkedlist;

import com.learning.list.singlelinkedlist.Node;

public class App {
    public static void main(String[] args) {

        //Circular Linked List Call
        CircularLinkedList myList = new CircularLinkedList();
        //myList.insertAtBeginning(100);
        //myList.insertAtBeginning(200);
        myList.insertAtBeginning(10);
        myList.insertAtBeginning(20);
        myList.insertAtBeginning(30);
        myList.insertAtBeginning(40);
        myList.insertAtBeginning(50);
        myList.insertAtBeginning(60);
        myList.insertAtBeginning(70);
        myList.insertAtBeginning(80);
        myList.insertAtBeginning(90);
        myList.insertAtBeginning(100);

        System.out.println("******Inserted At Beginning***********");
        myList.displayResults();
        System.out.println("**************************************");

        System.out.println("=======================================");

        System.out.println("******Inserted At End*****************");
        myList.insertAtEnd(200);
        myList.displayResults();
        System.out.println("**************************************");

        System.out.println("=======================================");

        System.out.println("******Delete At Beginning*************");
        Node deletedNodeAtBeginning = myList.deleteNodeAtBeginning();
        System.out.println(deletedNodeAtBeginning.data);
        System.out.println("**************************************");

        System.out.println("=======================================");

        System.out.println("******Delete At End*************");
        Node deletedNodeAtEnd = myList.deleteNodeAtEnd();
        System.out.println(deletedNodeAtEnd.data);
        System.out.println("**************************************");
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
