package HW2.serialize;

import java.io.*;

/**
 * @author Alexey
 */
public class TestSerialize {

    public void serialize(House house) throws IOException {
        FileOutputStream fos = new FileOutputStream("object.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(house);
        oos.flush();
        oos.close();
    }

    public House deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("object.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (House) oin.readObject();
    }


    public static void main(String[] args) {
        TestSerialize testSerialize=new TestSerialize();
        House house=new House();

        try {
            testSerialize.serialize(house);
            System.out.println(testSerialize.deserialize());

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
