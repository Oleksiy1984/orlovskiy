package HW2.Task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Задание 4
 *
 * @author Alexey
 */
public class Task4 {
    public static void print() {

        Map<String, String> hasmap = new HashMap<>();
        String line;

        try {
            Scanner s = new Scanner(new File("src/main/java/HW2/Task4/dictionary.txt"));
            while (s.hasNextLine()) {
                line = s.nextLine();
                String[] parts = line.split("\\s");
                hasmap.put(parts[0], parts[1]);
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(hasmap);
        //System.out.println(translateEnToRu(hasmap,"Perform"));
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Введите слово на английском:");
            String word = sc.next();
            System.out.println(translateEnToRu(hasmap, word.toLowerCase()));
            System.out.println("Continue(Y/N)?");
        }
        while (sc.next().equalsIgnoreCase("Y"));
        sc.close();
    }

    public static String translateEnToRu(Map<String,String> map,String en){
        if(map.containsKey(en)){
            return map.get(en);
        }

        return "There is no word \""+en+"\" in the dictionary!";
    }
}
