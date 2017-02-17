package HW1;


import HW1.SortArrays.Bubble;
import HW1.SortArrays.Mergesort;

import java.util.Arrays;
import java.util.Scanner;

public class Sort {
    public static void print() {

        int tenNums[]=new int[10];

        //User input
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 10 integers : ");
        for (int i=0;i<tenNums.length;i++)
        {
            System.out.print(i+1+"=> ");
            tenNums[i] = scan.nextInt();
        }
        System.out.println();

        //Sort
        //1. MergeSort
        Mergesort mergeSort=new Mergesort();
        mergeSort.sort(tenNums);
        System.out.println(Arrays.toString(tenNums));

        //2.Bubble.sort(tenNums);
        //3. Quicksort.sort(tenNums);
        //4. Insertion.sort(tenNums);
        //5. Selection.sort(tenNums);

/*
        //Find MAX and MIN
        int max = tenNums[0];
        int min = tenNums[0];
        for (int i = 1; i < tenNums.length; i++) {
            if (tenNums[i] > max) {
                max = tenNums[i];
            }
            if (tenNums[i] < min) {
                min = tenNums[i];
            }
        }
        System.out.println("MIN: "+min+" MAX: "+max);
 */

    }
}
