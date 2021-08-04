package com.learning.recursion;

public class Power {

    public int power(int base, int exp){
        if(exp < 0) return -1;
        if(exp == 0 || exp == 1) return base;
        return base * power(base, exp-1);
    }

    public static void main(String[] args){
        Power p = new Power();
        System.out.println(p.power(3,5));
        System.out.println(p.power(2,4));
        System.out.println(p.power(5,2));
    }
}
