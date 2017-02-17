package HW1;


import java.util.Scanner;

public class Switch {
    public static void print() {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the month: ");
        int month = scanner.nextInt();

       switch(month)  {

           case 12:
           case 1:
           case 2:
            System.out.println("Winter");
               break;
           case 3:
           case 4:
           case 5:
            System.out.println("Spring");
               break;
           case 6:
           case 7:
           case 8:
               System.out.println("Summer");
               break;
           case 9:
           case 10:
           case 11:
               System.out.println("Autumn");
               break;
        default:
            System.out.println("Invalid month.");
            break;
    }
}
 }