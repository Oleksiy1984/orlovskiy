package HW5;

import java.util.Set;

/**
 * @author Alexey.
 */
public class Engine {
    private int id;
    private double displacement;
    private int power;
    //@OneToMany
    private Set<Car> set;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Set<Car> getSet() {
        return set;
    }

    public void setSet(Set<Car> set)
    {
       this.set = set;
    }

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

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", displacement=" + displacement +
                ", power=" + power +
                '}';
    }

}
