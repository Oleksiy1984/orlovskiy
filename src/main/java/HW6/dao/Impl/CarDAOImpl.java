package HW6.dao.Impl;

import HW6.dao.CarDAO;
import HW6.model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;



public class CarDAOImpl implements CarDAO{

    public Car addCar(Car car) throws SQLException {

            try(Session session=factory.openSession()){
                Transaction tx = session.beginTransaction();
                session.save(car);
                tx.commit();
            } catch (Exception e) {e.printStackTrace();}
            return car;
        }

        public Car updateCar(Car car) throws SQLException {

            try(Session session=factory.openSession()){
                Transaction tx = session.beginTransaction();
                session.update(car);
                tx.commit();
            } catch (Exception e) { e.printStackTrace();}
            return car;
        }


        public Car getCarById(Long car_id) throws SQLException {
            Car car = null;
            try(Session session=factory.openSession()){
                car = session.get(Car.class, car_id);
            } catch (Exception e) {e.printStackTrace();}
            return car;
        }

    public void deleteCar(Car car) throws SQLException {
        try(Session session=factory.openSession()){
            Transaction tx = session.beginTransaction();
            session.delete(car);
            tx.commit();
        } catch (Exception e) {e.printStackTrace();}
    }
    }
