package HW6.dao;

import HW6.model.Car;
import HW6.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.sql.SQLException;


public interface CarDAO {
    SessionFactory factory= HibernateUtil.getSessionFactory();
    Car addCar(Car car) throws SQLException;
    Car updateCar(Car car) throws SQLException;
    Car getCarById(Long car_id) throws SQLException;
    void deleteCar(Car car) throws SQLException;
}
