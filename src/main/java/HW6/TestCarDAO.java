package HW6;


import HW6.dao.CarDAO;
import HW6.dao.Impl.CarDAOImpl;
import HW6.model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestCarDAO {

    private Car car=null;
    private CarDAO carDAO=null;
    private Transaction tx;
    private Session session;

    @Before
    public void setUp() throws Exception {

        carDAO = new CarDAOImpl();

        car = Car.builder().model("Toyota").make(new Date()).
                id_engine(1256).
                price(1000).
                date(new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01")).build();
    }

    @After
    public void cleanup() throws Exception {
        session.close();
        carDAO.factory.close();
        car=null;
    }

    @Test
    public void testAddCar() throws SQLException {

        //insert
        Car insertedCar = carDAO.addCar(car);
        Assert.assertNotNull(insertedCar.getId());
        Assert.assertEquals(car, insertedCar);
        //delete
        session = carDAO.factory.openSession();
            tx = session.beginTransaction();
            session.delete(car);
            tx.commit();
    }
    @Test
    public void testSelectCar() throws SQLException {
        //insert
        session = carDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(car);
        tx.commit();
        //select
        Car selectedCar = carDAO.getCarById(car.getId());
        Assert.assertNotNull(selectedCar.getId());
        Assert.assertEquals(car, selectedCar);
        //delete
        tx = session.beginTransaction();
        session.delete(car);
        tx.commit();
    }
    @Test
    public void testUpdateCar() throws SQLException {

        //insert
        session = carDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(car);
        tx.commit();
        //update
        Car newCar = carDAO.updateCar(car);
        Assert.assertEquals(car, newCar);
        System.out.println(newCar);
        //delete
        tx = session.beginTransaction();
        session.delete(car);
        tx.commit();
    }
    @Test
    public void testDeleteCar() throws SQLException {
        //insert
        session = carDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(car);
        tx.commit();
        session.close();
        //delete
        carDAO.deleteCar(car);
        //try to get car
        session = carDAO.factory.openSession();
        tx = session.beginTransaction();
        Car selectedCar = session.get(Car.class, car.getId());
        tx.commit();
        Assert.assertNull(selectedCar);
    }

}