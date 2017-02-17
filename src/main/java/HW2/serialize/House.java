package HW2.serialize;

import java.io.Serializable;

/**
 * @author Alexey
 */
public class House implements Serializable {
    private int count=2;
    private Color color =new Color();

    @Override
    public String toString() {
        return "House{" +
                "count=" + count +
                ", color=" + color.toString() +
                '}';
    }
}
