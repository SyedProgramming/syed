package com.learning.recursion;

public class FibonacciDemo {

    public int fibonacci(int n) {
        if(n < 0) return -1;
        if(n == 0 || n == 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args){
        FibonacciDemo fd = new FibonacciDemo();
        System.out.println(fd.fibonacci(6));
    }


}
