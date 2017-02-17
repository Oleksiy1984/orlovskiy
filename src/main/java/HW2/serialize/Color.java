package HW2.serialize;

import java.io.Serializable;

/**
 * @author Alexey
 */

public class Color implements Serializable {
    private int r=255;
    private String name="white";

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", name='" + name + '\'' +
                '}';
    }
}