package com.learnings.stack;

import java.util.stream.Stream;

public class ArrayStack {

	int size;
	int arr[];
	int top;
	
	ArrayStack(int size) {
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
		
		if(!isFull()) {
			top++;
			arr[top] = pushedElement;
			//System.out.println("Pushed Element : " + pushedElement);
		}
		else {
			System.out.println("Stack is Full..!");
		}
	}
		
	 public int pop() {
		 if(!isEmpty()) {
			 int returnTop = top;
			 top--;
			 //System.out.println("Poped Element : " + arr[returnTop]);
			 return arr[returnTop];
			  }
		 else {
			 System.out.println("Stack is Empty..!");
			 return -1;
		 }
	 }
		
		
		
	public int peek() {
		if(!isEmpty()) {
			return arr[top];
		}
		else {
			System.out.println("Stack is Empty..!");
			return -1;
		}
	}
	
	
	
	public void printStack(ArrayStack as) {
		while(!isEmpty()) {
			System.out.println(as.pop() + " ");
			}
	}
	
	public static void main(String[] args) {
		ArrayStack as = new ArrayStack(10);
		//as.pop();
		
		as.push(10);
		as.push(20);
		as.push(30);
		as.push(60);
		as.push(80);
		as.push(10);
		as.push(20);
		
		
		//as.pop();
		
		//as.peek();
		
		//as.push(40);
		
		as.printStack(as);
		System.out.println("--------");
		as.push(40);
		as.printStack(as);
		
	}
	
}















