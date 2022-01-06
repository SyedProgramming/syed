package src.com.learning.array;

public class SortArrayRotation {

    public static void main(String[] args) {
        int arr[] = { 3, 4, 5, 6, 7, 8, 9, 1, 2};
        int n = arr.length;


        int i = findNumber(arr, 0, n-1, 1);
        if(i != -1) {
            System.out.println("Index: " +i + " Value = " + arr[i]);
        }else {
            System.out.println("Value not found");
        }

    }

    static int findNumber(int arr[], int start, int end, int value) {
        System.out.println("**************************************");
        System.out.println("Array Length " + arr.length + " Start " +start + " End " +end + " Value " +value);
        System.out.println("**************************************");

        if(start > end)
            return -1;

        int mid = (start + end) / 2;


        if(arr[mid] == value)
            return mid;


        if(arr[start] <= arr[mid]) {
            if(value >= arr[start] && value <= arr[mid]){
                return findNumber(arr, start, mid - 1, value);
            }
            return findNumber(arr,mid+1, end, value);
        }

        if(value >= arr[mid] && value <= arr[end]) {
            return findNumber(arr, mid+1, end, value);
        }

        return findNumber(arr, start, mid-1, value);
    }

}
