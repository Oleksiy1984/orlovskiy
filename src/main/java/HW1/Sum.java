package HW1;


import java.util.Arrays;

public class Sum {
    public static void print() {

        int[][] array = new int[5][8];
        int sum = 0;

        System.out.println("Random array 5x8");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (int) (Math.random() * 100);
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }


        //Initialize one dimensional array with size equals number of column of initial array
        int size = array[0].length;
        int temp[] = new int[size];

        //Filling temporary array with column sum of the initial array
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                temp[j] += array[i][j];
            }
        }
        //Get element sum of the temporary array
        for (int i : temp) {
            sum += i;
        }
        System.out.println(Arrays.toString(temp));
        System.out.println("Sum: " + sum);
    }
}
