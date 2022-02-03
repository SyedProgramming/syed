package com.learning.stack;

public class StackArray {

    int size;
    int arr[];
    int top;

    StackArray(int size) {
        this.size = size;
        this.arr = new int[size];
        this.top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == size - 1);
    }

    public void push(int pushedElement) {
        if (!isFull()) {
            top++;
            arr[top] = pushedElement;
        } else {
            System.out.println("Stack is Full");
        }
    }

    public int pop() {
        if (!isEmpty()) {
            int popped_element = top;
            top--;
            return arr[popped_element];
        } else {
            System.out.println("Stack is Empty");
            return -1;
        }
    }

    public int peek() {
        if (!isEmpty()) {
            return arr[top];
        } else {
            System.out.println("Stack is Empty");
            return -1;
        }
    }

    public void printStack(StackArray sa) {
        while (!isEmpty()) {
            System.out.println(sa.pop());
        }
    }


    public static void main(String[] args) {
        StackArray sa = new StackArray(10);
        sa.push(10);
        sa.push(20);
        sa.push(30);
        sa.push(40);
        sa.push(50);
        sa.push(50);
        sa.push(50);
        sa.push(50);
        sa.push(50);
        sa.push(50);

        sa.printStack(sa);
        //System.out.println(sa.pop());


    }

}