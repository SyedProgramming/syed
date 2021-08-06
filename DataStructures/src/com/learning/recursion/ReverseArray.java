package com.learning.recursion;

import java.util.Arrays;
public class ReverseArray {

    static int[] a = {5,9,10,22,34,67,54,24,43};

    public void reverse(int[] array){
        for(int i=0; i<array.length/2; i++){
            int other = array.length-i-1;
            int temp = array[i];
            array[i] = array[other];
            array[other] = temp;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args){
        ReverseArray ra = new ReverseArray();
        ra.reverse(a);
    }
}
