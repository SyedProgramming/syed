package com.learning.array;

import java.util.Arrays;
public class PairTwoSum {

    static int intArray[] = {2,7,13,15};

    public int[] pairSum(int[] nums, int target) {
        for(int i=0; i < nums.length; i++) {
            for(int j=i+1; j< nums.length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[] {i, j};
                }
            }
         }
        throw new IllegalArgumentException("No Solution Found");
    }

    public static void main(String[] args){
        PairTwoSum p = new PairTwoSum();
       int[]  result = p.pairSum(intArray,9);
       System.out.println(Arrays.toString(result));
    }

}
