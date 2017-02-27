package HW6.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "stations")

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "make")
    @Temporal(TemporalType.TIMESTAMP)
    private Date make;

    @Column(name = "id_engine")
    private int id_engine;

    @Column(name = "price")
    private int price;

    @Column(name = "date")
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "car_ss", joinColumns = {
            @JoinColumn(name = "id_car", referencedColumnName = "id",nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_ss", referencedColumnName = "id",
                    nullable = false, updatable = false)})
    private Set<ServiceStations> stations = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (price != car.price) return false;
        if (!id.equals(car.id)) return false;
        if (!model.equals(car.model)) return false;
        if (!make.equals(car.make)) return false;
        return date.equals(car.date);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + make.hashCode();
        result = 31 * result + price;
        result = 31 * result + date.hashCode();
        return result;
    }
}
