package com.learning.recursion;

public class DecimalToBinary {

    public int decimalToBinary(int n) {
        /*
        f(n) = n%2+10 * f(n/2)
        */

        if(n == 0) return 0;
        return n%2+10 * decimalToBinary(n/2);
    }

    public static void main(String[] args){
        DecimalToBinary d = new DecimalToBinary();
        System.out.println(d.decimalToBinary(10));
    }

}
