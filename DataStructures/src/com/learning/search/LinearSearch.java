package com.learning.search;

public class LinearSearch {

    static int[] intArray = {2,5,4,7,8,1,7,0};

    public static int linearSearch(int[] intArray, int x) {
        for(int i=0; i<intArray.length; i++){
            if(intArray[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Found Item at Index : " + linearSearch(intArray,1));
    }


}
