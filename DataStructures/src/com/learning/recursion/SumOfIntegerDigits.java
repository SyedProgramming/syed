package com.learning.recursion;

public class SumOfIntegerDigits {

    public int sumOfDigits(int n){
        if(n < 0 || n == 0) return 0;
        return (n%10) + sumOfDigits(n/10);
    }

    public static void main(String[] args){
        SumOfIntegerDigits s = new SumOfIntegerDigits();
        System.out.println(s.sumOfDigits(114));
    }

}
