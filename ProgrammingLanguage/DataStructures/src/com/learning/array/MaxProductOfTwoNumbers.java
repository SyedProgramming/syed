package com.learning.array;

public class MaxProductOfTwoNumbers {

    static int[] nums = {43,78,54,67,54,59};
    int maxProductNumber=0;
    String pairs="";

    public String maxProduct(int[] numsArray) {
        for (int i = 0; i < numsArray.length; i++){
            for(int j=i+1; j<numsArray.length;j++){
                if(numsArray[i]*numsArray[j] > maxProductNumber) {
                    maxProductNumber = numsArray[i]*numsArray[j];
                    pairs = "("+Integer.toString(numsArray[i]) + "," + Integer.toString(numsArray[j])+")";
                }
            }
        }
        return pairs;
    }


    public static void main(String[] args){
        MaxProductOfTwoNumbers m = new MaxProductOfTwoNumbers();
        System.out.println(m.maxProduct(nums));

    }
}
