package HW4.Calculator;


import java.util.Scanner;

/**
 * @author Alexey.
 */
public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if(b == 0) {
            throw new ArithmeticException();
        }
        return a / b;
    }

    public static void main(String[] args) {
        double firstnumber, secondnumber;
        int selection;
        try(Scanner var = new Scanner(System.in)){

        System.out.println("Your options:");
        System.out.println("1. Addition \t\t\t+\t\t");
        System.out.println("2. Subtraction \t\t\t-\t\t");
        System.out.println("3. Division \t\t\t/\t\t");
        System.out.println("4. Multiplication \t\t*\t\t");
        int i=0;
            while(i<10){
                System.out.print("Choice :");
        selection = var.nextInt();
        System.out.print((char) 27 + "[34mEnter First number = ");
        firstnumber = var.nextDouble();
        System.out.print((char) 27 + "[34mEnter Second number = ");
        secondnumber = var.nextDouble();
        Calculator calc =new Calculator();
        switch(selection){
            case 1:
                System.out.println("Result = "+calc.add(firstnumber,secondnumber));
                break;
            case 2:
                System.out.println("Result = "+calc.subtract(firstnumber,secondnumber));
                break;
            case 3:
                System.out.println("Result = "+calc.divide(firstnumber,secondnumber));
                break;
            case 4:
                System.out.println("Result = "+calc.multiply(firstnumber,secondnumber));
                break;
            default:
                System.out.println("No such operation!");
        }
                System.out.println("Continue Y/N?");
                String s1 = var.next();
                if(!s1.toLowerCase().equals("y")){break;}
                i++;
            }

}

    }
}
