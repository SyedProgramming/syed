package com.learning.queue;

public class QueueArray {
    static int front, rear, capacity;
    int queue[];

    public QueueArray(int size){
        front = rear = 0;
        capacity = size;
        queue = new int[capacity];
    }


    // insert an element into the queue
    public void queueEnqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            queue[rear] = item;
            rear++;
        }
    }

    // remove an element from the queue
    public void queueDequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return;
        }else {
            // shift elements to the right by one place until rear
            for (int i = 0; i < rear - 1; i++) {
                queue[i] = queue[i + 1];
            }
        }
         //set queue[rear] to 0
         if(rear < capacity)
             queue[rear] = 0;

         rear--;

    }

    // check is queue is full
    public boolean isFull(){
        return (rear == capacity);
        }

    // check is queue is empty
    public boolean isEmpty(){
        return (front == rear);
    }

    // print queue elements
    public void displayQueue(){
        int i;
        if(isEmpty()){
            System.out.println("Queue is Empty");
        }else{
            for(i=0; i<rear;i++){
                System.out.println(queue[i]);
            }
        }
    }

    public static void main(String[] args){
        QueueArray qa = new QueueArray(5);
        qa.queueEnqueue(10);
        qa.queueEnqueue(20);
        qa.queueEnqueue(30);
        qa.queueEnqueue(40);
        qa.queueEnqueue(50);
        qa.queueEnqueue(60);

        //qa.queueEnqueue(60);
        System.out.println("*******************************");
        qa.displayQueue();
        System.out.println("*******************************");

        qa.queueDequeue();

        qa.queueEnqueue(70);
        qa.queueEnqueue(80);
        System.out.println("*******************************");
        qa.displayQueue();
        System.out.println("*******************************");

        System.out.println("*******************************");
        System.out.println("Rear = " + QueueArray.rear);
        System.out.println("Front = " + QueueArray.front);
        System.out.println("Capacity = " + QueueArray.capacity);
        System.out.println("*******************************");

    }

}
