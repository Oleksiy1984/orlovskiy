package HW2.IO;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alexey.
 */
public class Task8 {
    public static void print() {
        writeToFile();
        readFromFile();
    }
    private static void writeToFile(){

        List<String> lines = new ArrayList<>();
        System.out.println("Enter text, press Enter and Ctrl+D at the end: ");

        //automatic resource management in java 7 is a great feature!
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }

        try (FileOutputStream fos = new FileOutputStream("src/main/java/HW2/IO/text.txt");
             PrintStream printStream = new PrintStream(fos)) {
            for (String line : lines) {
                printStream.println(line);
            }
            System.out.println("Запись в файл произведена");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
private static void readFromFile(){

    System.out.println("Чтение из файла:");

    FileInputStream fin = null;

    try {
        File file = new File("src/main/java/HW2/IO/text.txt");
        fin=new FileInputStream(file);
        InputStreamReader charsIn = new InputStreamReader(fin, "UTF-8");
        BufferedReader in = new BufferedReader(charsIn);

        int i;
        while ((i = in.read()) != -1) {

            System.out.print((char) i);
        }
        in.close();
    }
    catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    finally {

        try {

            if (fin != null) {
                fin.close();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}

}



