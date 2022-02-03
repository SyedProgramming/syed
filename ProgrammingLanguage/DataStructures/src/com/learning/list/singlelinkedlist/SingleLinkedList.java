package com.learning.list.singlelinkedlist;

public class SingleLinkedList {
    private Node first;

    public SingleLinkedList(){

    }

    public boolean isEmpty(){
        return (first == null);
    }

    public void insertAtBeginning(int nodeDataToBeInserted){
        Node newNode = new Node();
        newNode.data = nodeDataToBeInserted;

        newNode.next = first;
        first = newNode;
    }


    public void insertAtEnd(int nodeDataToBeInserted) {
        Node newNode = new Node();
        newNode.data = nodeDataToBeInserted;

        Node currentNode = first;
        while(currentNode.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = newNode;

    }

    public Node deleteNodeAtBeginning(){
        Node deletedNode = first;
        first = first.next;
        return deletedNode;
    }

    public void displayResults(){
        System.out.println("The List contains the following: ");
        Node current = first;

        while(current != null){
            current.displayNode();
            current = current.next;
        }
        System.out.println();
    }


}
