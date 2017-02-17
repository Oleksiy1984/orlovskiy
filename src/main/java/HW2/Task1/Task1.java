package HW2.Task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Задание 1
 *
 * @author Alexey
 */
public class Task1 {

    public static void print()   {
        ArrayList<String> list = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File("src/main/java/HW2/Task1/employees.txt"));
            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }
            s.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        Random random = new Random();
        int index = random.nextInt(list.size());
        System.out.println(list.get(index) + " gets bonus this month!");
    }

}
