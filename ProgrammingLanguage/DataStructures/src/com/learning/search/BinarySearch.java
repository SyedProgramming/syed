package com.learning.search;

public class BinarySearch {

    static int[] intArray = {1,3,5,6,9,10,13,14,16,18,19,21,23,24,26,28,34,56,78,90}; // Should be in sorted order

    public static int binarySearch(int[] intArray, int x){
            int p=0;
            int r=intArray.length-1;

            while(p <= r){
                int q = (p+r)/2;
                if(x < intArray[q]){
                    r = q-1;
                }else if (x > intArray[q]) {
                    p = q+1;
                }else return q;
            }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Found Item at Index : " + binarySearch(intArray,10));
    }
}
