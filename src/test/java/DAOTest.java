import HW5.Car;
import HW5.ConnectorDB;
import HW5.DAOManager;
import HW5.Engine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.text.SimpleDateFormat;

/**
 * @author Alexey.
 */
public class DAOTest {

    private DAOManager dao;
    private Car car;
    private Engine engine;
    private Connection cn;

    @Before
    public void setUp() throws Exception {
        //get connection
        cn = ConnectorDB.getConnection();
        //begin transaction
        cn.setAutoCommit(false);
        //create test objects
        dao = new DAOManager(cn);
        car = new Car();
        engine = new Engine();
        engine.setPower(90);
        engine.setDisplacement(2.2);
        car.setEngine(engine);
        car.getEngine().setId(1);
        car.setMake(new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01"));
        car.setPrice(99000);
        car.setModel("BMW3");
    }

    @After
    public void cleanup() throws Exception {
        cn=null;
        dao=null;
        car = null;
        engine=null;
    }

    @Test
    public void testAll() throws Exception {

        try {
            //test insertCar
            Car insertedCar = dao.insertCar(car);
            car.setId(insertedCar.getId());
            Assert.assertEquals(insertedCar, car);

            //test getCarById
            Car selectedCar = dao.getCarById(insertedCar.getId());
            Assert.assertEquals(selectedCar, car);

            //test insertEngine
            Engine insertedEngine=dao.insertEngine(engine);
            engine.setId(insertedEngine.getId());
            Assert.assertEquals(insertedEngine,engine);

            //test getEngineById
            Engine selectedEngine=dao.getEngineById(insertedEngine.getId());
            Assert.assertEquals(selectedEngine,engine);

            //checkEngineId
            Assert.assertEquals(true,dao.checkEngineId(insertedEngine.getId()));

        }
        finally {
            cn.rollback(); //aborts the transaction
            cn.close();
        }
    }

}
