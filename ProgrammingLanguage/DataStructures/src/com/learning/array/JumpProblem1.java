package src.com.learning.array;

public class JumpProblem1 {

    private static int jump(int[] num) {
        int i = 0;
        int a = num[0];
        int b = num[0];
        int jump = 1;

        for (i=1; i <= num.length; i++){

            if(i == num.length - 1) {
                return jump;
            }

            a--;
            b--;

            if(num[i] > b){
                b = num[i];
            }

            if(a == 0){
                jump++;
                a=b;
            }
        }
        return jump;

    }


    public static void main(String[] args) {
        int num[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};

        System.out.println("Minimum number of jumps to reach end is : " + jump(num));
    }

}
