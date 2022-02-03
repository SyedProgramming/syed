package src.com.learning.array;

public class MoveZeroToEnd {

    public static void main(String[] args) {
        int arr[] = {1, 3, 0, 0, 4, 0, 9, 1};
        int n = arr.length;

        moveZeroToEnd(arr, n);

        System.out.println("Result is ");

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

    }


        public static void moveZeroToEnd(int arr[], int n) {
            int cnt=0;

            for(int i=0; i<n; i++){
                if(arr[i] != 0) {
                    arr[cnt++] = arr[i];
                }
            }

            while(cnt < n) {
                arr[cnt++] = 0;
            }

        }




}
