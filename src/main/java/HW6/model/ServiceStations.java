package HW6.model;

import lombok.*;
import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stations")
    private Set<Mechanic> mechanics = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "stations")
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
