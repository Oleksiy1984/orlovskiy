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
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", displacement=" + displacement +
                ", power=" + power +
                '}';
    }

}
