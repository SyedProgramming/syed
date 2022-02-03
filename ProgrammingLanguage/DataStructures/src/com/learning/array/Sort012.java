package src.com.learning.array;
import java.util.Arrays;

public class Sort012 {

        public static void main(String[] args) {
            int array[] = {2, 1, 1 , 0, 1, 2, 1, 2, 0, 0, 0, 1};
            // 1, 1, 1 , 0, 1, 2, 1, 2, 0, 0, 0, 2
            //


            sort012(array);
            System.out.println(Arrays.toString(array));
            //System.out.println("The Sorted Array " + sort012(array));
        }


        private static void swap(int[] arr, int i, int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public static void sort012(int[] arr){
            int start = 0, mid = 0;
            int end = arr.length - 1;
            int pivot = 1;

            while(mid <= end) {

                if(arr[mid] == 0) {
                    swap(arr, start, mid);
                    ++start;
                    ++mid;
                }
                else if(arr[mid] > 1) {
                    swap(arr, mid, end);
                    --end;
                }
                else {
                    ++mid;
                }
            }
        }
}
