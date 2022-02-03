package com.learning.array;

public class ContainsDuplicate {
    static int[] nums = {43, 78, 54, 67, 55, 59};

    public boolean isUnique(int[] numsArray) {

        for (int i = 0; i < numsArray.length; i++) {
            for (int j = i + 1; j < numsArray.length; j++) {
                if (numsArray[i] == numsArray[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ContainsDuplicate cd = new ContainsDuplicate();
        System.out.println(cd.isUnique(nums));
    }

}
