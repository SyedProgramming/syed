package com.learning.list.circularlinkedlist;

import com.learning.list.singlelinkedlist.Node;
import sun.lwawt.macosx.CSystemTray;

public class CircularLinkedList {

    private static Node first;
    private static Node last;

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertAtBeginning(int nodeDataToBeInserted) {
        Node newNode = new Node();
        newNode.data = nodeDataToBeInserted;

        if(isEmpty()) {
            first = newNode;
            last = newNode;
            newNode.next = first;
        }else {
            newNode.next = first;
            first = newNode;
            last.next = first;
        }
    }


    public void insertAtEnd(int nodeDataToBeInserted) {
        Node newNode = new Node();
        newNode.data = nodeDataToBeInserted;

        if (isEmpty()) {
            first = newNode;
            last = newNode;
            newNode.next = first;
        } else {
            last.next = newNode;
            last = newNode;
            last.next = first;
        }
    }

    public Node deleteNodeAtBeginning(){
        Node deletedNode = first;

        if(isEmpty()){
            first = null;
            last = null;
        }
        else {
            first = first.next;
            last.next = first;
        }
        return deletedNode;
    }

    public Node deleteNodeAtEnd(){
        Node deletedNode = last;

        if(isEmpty()){
            first = null;
            last = null;
        }
        else {
            int compareLength = getCircularLength() - 1;
            Node current = first;
            int length=0;
            while(current.next != first){
                current = current.next;
                length++;

                if(length == compareLength){
                    System.out.println("Current Length Data is " + current.data);
                    System.out.println("Deleted Node Value is  " + current.next.data);

                    current.next = first;

                    break;
                }
            }


        }
        return deletedNode;
    }


    public void displayResults(){
        System.out.println("The List contains the following: ");

        Node current = first;

        do {
            current.displayNode();
            current = current.next;
        } while(current != first);

    }

    public static int getCircularLength() {
        int length=0;
        Node currentNode = first;
        while(currentNode.next != first){
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }
}















