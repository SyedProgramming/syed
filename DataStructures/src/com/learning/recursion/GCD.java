package com.learning.recursion;

// Greatest Common Divisor
public class GCD {

    /******************
     Euclidean Algorithm
     g(b,0) = a
     g(a,b) = g(b, a%b)

     STEP-1: gcd(48,18) ==> 2 remainder 12
     STEP-2: gcd(18,12) ==> 1 remainder 6
     STEP-3: gdc(12,6)  ==> 2 remainder 0
     *******************/

    public int gcd(int a, int b) {
        if(a < 0 || b < 0) return -1;
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public static void main(String[] args){
        GCD g = new GCD();
        System.out.println(g.gcd(8,4));
    }




}
