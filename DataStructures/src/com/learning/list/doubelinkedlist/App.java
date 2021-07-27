package com.learning.list.doubelinkedlist;

public class App {

    public static void main(String[] args) {
        DoubleLinkedList mylinkedList = new DoubleLinkedList();
        mylinkedList.insertFirst(10);
        mylinkedList.insertLast(90);
        mylinkedList.insertFirst(5);
        mylinkedList.insertAfter(5, 79);
        mylinkedList.insertFirst(1);
        mylinkedList.insertLast(95);
        mylinkedList.insertLast(100);
        mylinkedList.deleteFirst();
        mylinkedList.deleteLast();
        System.out.println(mylinkedList.deleteKey(95));
        mylinkedList.displayList();
    }
}
