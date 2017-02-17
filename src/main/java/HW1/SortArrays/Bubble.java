package HW1.SortArrays;


import java.util.Arrays;

public class Bubble {

    public static void sort(int tenNums[]) {

        /* 1.Bubble sort
        * Worst-case performance	 O(n^2)
        * Best-case performance	      O(n)
        * Average performance	     O(n^2)
        */

        System.out.println("Bubble sort:");

        //Using temporary variable in ascending order
        for (int i = 0; i < tenNums.length; i++) {
            for (int j = i + 1; j < tenNums.length; j++) {
                int tmp;
                if (tenNums[i] > tenNums[j]) {
                    tmp = tenNums[i];
                    tenNums[i] = tenNums[j];
                    tenNums[j] = tmp;
                }
            }

    }
        System.out.println(Arrays.toString(tenNums));
        System.out.println("MAX: "+tenNums[tenNums.length-1]+"\nMIN: "+tenNums[0]);

        /*
         //Using element change approach in descending order
        for (int i = tenNums.length-1; i >=0; i--) {
            for (int j = i - 1; j >=0; j--) {
                if (tenNums[i] > tenNums[j]) {
                    tenNums[i] = tenNums[i] + tenNums[j];
                    tenNums[j] = tenNums[i] - tenNums[j];
                    tenNums[i] = tenNums[i] - tenNums[j];

                }
            }
        }
        System.out.println(Arrays.toString(tenNums));
        System.out.println("MIN: "+tenNums[tenNums.length-1]+"\nMAX: "+tenNums[0]);
        */
    }
}
