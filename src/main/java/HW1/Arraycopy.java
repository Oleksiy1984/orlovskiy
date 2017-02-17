package HW1;


import java.util.Arrays;

public class Arraycopy {
    public static void print() {

        int[] array = new int[10];

        System.out.println("Random array: ");
        for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * 100);
            }
        System.out.println(Arrays.toString(array));
        System.out.println("Array with first 3 element at the end of the array: ");
        System.arraycopy(array, 0, array, 7, 3);
        System.out.println(Arrays.toString(array));

        }
    }

