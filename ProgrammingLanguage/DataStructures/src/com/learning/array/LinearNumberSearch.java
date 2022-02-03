package com.learning.array;

public class LinearNumberSearch {

    static int[] nums = {43,78,54,67,54,59};
    public void searchNumber(int[] intArray, int number) {
        for(int i = 0; i < intArray.length; i++){
            if(intArray[i] == number){
                System.out.println("Number found at " + i +" position");
                return;
            }
        }
        System.out.println(number + " is not found");
    }

    public static void main(String[] args){
        LinearNumberSearch l = new LinearNumberSearch();
        l.searchNumber(nums, 54);
    }


}
