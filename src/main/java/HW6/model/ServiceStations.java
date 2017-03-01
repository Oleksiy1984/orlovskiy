package HW6.model;

import lombok.*;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "service_stations")
public class ServiceStations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "station")
    private Set<Mechanic> mechanics = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "car_ss", joinColumns = {
            @JoinColumn(name = "id_ss", referencedColumnName = "id",nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_car", referencedColumnName = "id",
                    nullable = false, updatable = false)})
    private Set<Car> cars = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceStations that = (ServiceStations) o;

        if (!id.equals(that.id)) return false;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
