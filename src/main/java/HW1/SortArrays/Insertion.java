package HW1.SortArrays;


import java.util.Arrays;

public class Insertion {

    /*
Worst-case performance	О(n2) comparisons, swaps
Best-case performance	O(n) comparisons, O(1) swaps
Average performance	О(n2) comparisons, swaps
     */

    public static void sort( int [] num)
    {
        int j;                     // the number of items sorted so far
        int key;                // the item to be inserted
        int i;

        for (j = 1; j < num.length; j++)    // Start with 1
        {
            key = num[j];
            for(i = j - 1; (i >= 0) && (num[i] < key); i--)   // Smaller values are moving up
            {
                num[i+1] = num[i];
            }
            num[i+1] = key;    // Put the key in its proper location
        }
        System.out.println("Insertion sort:");
        System.out.println(Arrays.toString(num));
        System.out.println("MIN: "+num[num.length-1]+"\nMAX: "+num[0]);
    }
}
