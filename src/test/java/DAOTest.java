import HW5.Car;
import HW5.DAOManager;
import HW5.Engine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alexey.
 */
public class DAOTest {

    private DAOManager dao;
    private Car car;
    private Engine engine;

    //Will be run before any test in the class
    //Creation of object which will be used in all the test cases
    @Before
    public void setUp() throws Exception {
        dao=new DAOManager();
        car=new Car();
        engine=new Engine();
        System.out.println("Before");
    }

    //Execute this method after executing
    //all the test cases. Include all clean up logics here
    @After
    public void tearDown() throws Exception {
        dao=null;
        car = null;
        engine=null;
        System.out.println("After");
    }

    @Test
    public void getCarById() throws ParseException {

        car = dao.getCarById(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = "2010-01-01";
        Date date = sdf.parse(dateAsString);
        System.out.println(car);
        //car spec
        Assert.assertEquals("BMW3", car.getModel());
        Assert.assertEquals(date, car.getMake());
        Assert.assertEquals(50000, car.getPrice());
        //engine spec
        Assert.assertEquals(1, car.getEngine().getId());
        Assert.assertEquals(2.2, car.getEngine().getDisplacement(),0.001);
        Assert.assertEquals(90, car.getEngine().getPower());
    }

    @Test
    public void getEngineById()  {
        engine=dao.getEngineById(1);
        System.out.println(engine);
        System.out.println(engine.getSet());
        Assert.assertEquals(1, engine.getId());
        Assert.assertEquals(2.2, engine.getDisplacement(),0.001);
        Assert.assertEquals(90, engine.getPower());
    }

    @Test
    public void insertCar() throws ParseException {

        engine.setId(10);
        engine.setPower(120);
        engine.setDisplacement(3);
        car.setModel("Volvo");
        car.setMake(new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-11"));
        car.setPrice(155000);
        car.setEngine(engine);
        //insert car and get id from database
        Car c=dao.insertCar(car);
        int id_fromDB=c.getId();
        Assert.assertNotNull(id_fromDB);
        //get inserted car from database
        Car newCar=dao.getCarById(id_fromDB);
        Assert.assertEquals(id_fromDB, newCar.getId());
        Assert.assertEquals("Volvo", newCar.getModel());
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-11"), newCar.getMake());
        Assert.assertEquals(10, newCar.getEngine().getId());
        Assert.assertEquals(155000, newCar.getPrice());
    }

    @Test
    public void insertEngine() {

        engine.setPower(40);
        engine.setDisplacement(0.7);
        //insert engine and get id from database
        Engine e=dao.insertEngine(engine);
        int id_fromDB=e.getId();
        Assert.assertNotNull(id_fromDB);
        //get inserted engine from database
        Engine newEngine=dao.getEngineById(id_fromDB);
        System.out.println(newEngine);
        Assert.assertEquals(id_fromDB, newEngine.getId());
        Assert.assertEquals(40, newEngine.getPower());
        Assert.assertEquals(0.7, newEngine.getDisplacement(),0.001);
    }

    @Test
    public void emptyGetEngineById(){
        engine=dao.getEngineById(100);
        Assert.assertEquals(0, engine.getId());
    }

    @Test
    public void checkEngineId(){
        Assert.assertEquals(true,dao.checkEngineId(8));
    }

    @Test
    public void insertCarWithNoAvailableEngine() throws ParseException {

        engine.setId(100);
        engine.setPower(120);
        engine.setDisplacement(3);
        car.setModel("Audi");
        car.setMake(new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-11"));
        car.setPrice(65000);
        car.setEngine(engine);
        //try to insert car to database
        Car newCar=dao.insertCar(car);
        Assert.assertEquals(0, newCar.getId());
    }
}
