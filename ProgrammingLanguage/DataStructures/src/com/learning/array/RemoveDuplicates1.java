package src.com.learning.array;

public class RemoveDuplicates1 {

    private static int removeDuplicates(int[] arr, int n) {

        if (n == 0 || n == 1) {
            return n;
        }

        int j = 0;

        for (int i = 0; i < n-1; i++) {
            System.out.println("------------------");
            System.out.println("Value of N " + n);
            System.out.println("Value of i " + i);
            System.out.println("------------------");
            if(arr[i] != arr[i+1]){
                arr[j++] = arr[i];
            }
        }

        arr[j++] = arr[n-1];
        return j;
    }

    public static void main(String[] args){
        int array[] = {1, 2, 2, 3, 4, 4, 4, 5, 5};
        int n = array.length;


        n = removeDuplicates(array, n);

        for (int i=0; i<n; i++)
            System.out.print(array[i]+" ");
    }

}
