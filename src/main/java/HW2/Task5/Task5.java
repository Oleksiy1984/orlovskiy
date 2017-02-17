package HW2.Task5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Задание 5
 *
 * @author Alexey
 */
public class Task5 {
    public static void print() {
        SortedSet<String> set = new TreeSet<>();

        try {
            Scanner s = new Scanner(new File("src/main/java/HW2/Task5/songs.txt"));
            while (s.hasNextLine()) {
                set.add(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(String song:set){
            System.out.println(song);
        }

    }
}
