package HW5;

import java.util.Date;

/**
 * @author Alexey.
 */
public class Car {
   private int id;
   private String model;
   private Date make;
   private int price;
   //@ManyToOne
   private Engine engine;

   public int getId() {
      return id;
   }


   public void setId(int id) {
      this.id = id;
   }

   public String getModel() {
      return model;
   }

   public void setModel(String model) {
      this.model = model;
   }

   public Date getMake() {
      return make;
   }

   public void setMake(Date make) {
      this.make = make;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public Engine getEngine() {
      return engine;
   }

   public void setEngine(Engine engine) {
      this.engine = engine;
   }

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

   @Override
   public String toString() {
      return "Car{" +
              "id=" + id +
              ", model='" + model + '\'' +
              ", make=" + make +
              ", price=" + price +
              ", engine=" + engine +
              '}';
   }
}
