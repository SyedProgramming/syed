package com.learning.array;

public class Permutation {

    static int[] num1 = {5,4,6,7,8};
    static int[] num2 = {7,4,8,5,6};

    public boolean isPermutation(int[] array1, int[] array2){
        if(array1.length != array2.length){
            return false;
        }

        int sum1=0;
        int sum2=0;
        int mul1=1;
        int mul2=1;

        for(int i=0; i<array1.length; i++){
            sum1 += array1[i];
            sum2 += array2[i];

            mul1 *= array1[i];
            mul2 *= array2[i];
        }

        if(sum1 == sum2 && mul1 == mul2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        Permutation p = new Permutation();
        System.out.println(p.isPermutation(num1, num2));
    }
}
