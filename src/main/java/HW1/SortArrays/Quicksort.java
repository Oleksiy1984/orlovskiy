package HW1.SortArrays;


import java.util.Arrays;


public class Quicksort {

    public static void sort(int tenNums[]) {
        System.out.println("Sort using Java.util.Arrays.sort(int[])");
        Arrays.sort(tenNums);
        System.out.println("MAX: "+tenNums[tenNums.length-1]+"\nMIN: "+tenNums[0]);
    }

}
