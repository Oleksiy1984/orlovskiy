package HW6;


import HW6.dao.Impl.CarDAOImpl;
import HW6.model.Car;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDAO {

    private CarDAOImpl carDAO = null;
    private Car car = null;


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
        carDAO.tx.commit();
        carDAO.session.close();
        carDAO.factory.close();
        carDAO=null;
        car=null;
    }

    @Test
    public void testAddCar() throws SQLException {

        //insert
        Car insertedCar = carDAO.addCar(car);
        Assert.assertNotNull(insertedCar.getId());
        car.setId(insertedCar.getId());
        Assert.assertEquals(car, insertedCar);
        System.out.println(car);
        //delete
        carDAO.session.delete(car);
    }
    @Test
    public void testSelectCar() throws SQLException {
        //insert
        carDAO.session.save(car);
        //select
        Car selectedCar = carDAO.getCarById(car.getId());
        Assert.assertNotNull(selectedCar.getId());
        Assert.assertEquals(car, selectedCar);
        //delete
        carDAO.session.delete(car);
    }
    @Test
    public void testUpdateCar() throws SQLException {
        //insert
        carDAO.session.save(car);
        //update
        car.setPrice(56500);
        Car newCar = carDAO.updateCar(car);
        Assert.assertEquals(car, newCar);
        System.out.println(newCar);
        //delete
        carDAO.session.delete(car);
    }
    @Test
    public void testDeleteCar() throws SQLException {
        //insert
        carDAO.session.save(car);
        //delete
        carDAO.deleteCar(car);
        Assert.assertNull(carDAO.session.get(Car.class, car.getId()));
    }

}