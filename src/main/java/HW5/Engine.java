package HW5;

import lombok.*;

import java.util.Set;

/**
 * @author Alexey.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "set")
public class Engine {
    private int id;
    private double displacement;
    private int power;
    //@OneToMany
    private Set<Car> set;


    @Override
    public boolean equals(Object o) {

        if ( !(o instanceof Engine)) return false;

        Engine engine = (Engine) o;

        if (id != engine.id) return false;
        if (Double.compare(engine.displacement, displacement) != 0) return false;
        return power == engine.power;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(displacement);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + power;
        return result;
    }


}
