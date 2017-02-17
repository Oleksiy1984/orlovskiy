package HW1.SortArrays;


import java.util.Arrays;

public class Selection {

    /*
Worst-case performance	О(n2)
Best-case performance	О(n2)
Average performance	О(n2)
*/

    public static void sort( int [] num)
    {
        int i, j, first, temp;
        for ( i = num.length - 1; i > 0; i--)
        {
            first = 0;   //initialize to subscript of first element
            for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
            {
                if( num[j] < num[first] )
                    first = j;
            }
            temp = num[first];   //swap smallest found with element in position i.
            num[first] = num[ i ];
            num[i] = temp;
        }
        System.out.println("Selection sort:");
        System.out.println(Arrays.toString(num));
        System.out.println("MIN: "+num[num.length-1]+"\nMAX: "+num[0]);
    }
}
