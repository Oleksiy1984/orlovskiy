package HW6.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "mechanic")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", insertable=false, updatable=false)
    private ServiceStations stations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mechanic mechanic = (Mechanic) o;

        if (!id.equals(mechanic.id)) return false;
        if (!name.equals(mechanic.name)) return false;
        return surname.equals(mechanic.surname);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }
}
