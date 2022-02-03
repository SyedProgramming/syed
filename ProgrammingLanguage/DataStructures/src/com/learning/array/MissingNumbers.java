package com.learning.array;

// Find the single digit missing number in an integer array of 1 to 10
public class MissingNumbers {

    public static void missingNumber(int[] intArray) {
        int sum1 = 0;
        int sum2 = 0;

        for(int i : intArray){
            sum1 += i;
        }

        sum2 = 10*(10+1)/2; // n(n+1)/2
        int difference = sum2 - sum1;
        System.out.println("Missing Number " + difference);
    }

    public static void main(String[] args) {
        int intArray[] = {1, 2, 3, 4, 5, 6, 8, 9, 10};
        missingNumber(intArray);
    }

}
