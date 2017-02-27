package HW6.dao;

import HW6.model.Car;
import java.sql.SQLException;


public interface CarDAO {
    Car addCar(Car car) throws SQLException;
    Car updateCar(Car car) throws SQLException;
    Car getCarById(Long car_id) throws SQLException;
    void deleteCar(Car car) throws SQLException;
}
