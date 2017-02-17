package HW2.Task6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Задание 6
 * @author Alexey
 */
public class Task6 {
    public static void print() {
        String line;

        //Sort by Title
        class SortByTitle implements Comparator<Song>{

            @Override
            public int compare(Song o1, Song o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                //if Titles equal sort by Artist
                if(o1.getTitle().equals(o2.getTitle())){
                    return o1.getArtist().compareTo(o2.getArtist());
                }
                return o1.getTitle().compareTo(o2.getTitle());
            }

        }


        SortedSet<Song> set = new TreeSet<>();
        SortedSet<Song> setByTitle = new TreeSet<>(new SortByTitle());
        try {
            Scanner s = new Scanner(new File("src/main/java/HW2/Task6/songs6.txt"));
            while (s.hasNextLine()) {
                line = s.nextLine();
                String[] parts = line.split("/");
                setByTitle.add(new Song(parts[0],parts[1],Double.parseDouble(parts[2])));
                set.add(new Song(parts[0],parts[1],Double.parseDouble(parts[2])));
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i=1;
       System.out.println("Sort By Artist");
        for(Song s:set){

            System.out.println(i+". "+s.getArtist()+"-"+s.getTitle());
            i++;
        }
        System.out.println();
        System.out.println("Sort By Title");
        int i1=1;
        for(Song s:setByTitle){
            System.out.println(i1+". "+s.getTitle()+"-"+s.getArtist());
            i1++;
        }

    }
}
