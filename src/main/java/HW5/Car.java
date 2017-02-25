package HW5;

import lombok.*;

import java.util.Date;

/**
 * @author Alexey.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
   private int id;
   private String model;
   private Date make;
   private int price;
   //@ManyToOne
   private Engine engine;


   @Override
   public boolean equals(Object o) {
      if ( !(o instanceof Car)) return false;

      Car car = (Car) o;

      if (id != car.id) return false;
      if (!model.equals(car.model)) return false;
      return make.equals(car.make);
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + model.hashCode();
      result = 31 * result + make.hashCode();
      return result;
   }

}
