import HW5.Car;
import HW5.ConnectorDB;
import HW5.DAOManager;
import HW5.Engine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
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

        Connection cn = null;
        Statement st = null;
        PreparedStatement pst = null;
        int id=0;

        //insert car into database
        try {
            cn = ConnectorDB.getConnection();
            st=cn.createStatement();
            st.executeUpdate("INSERT INTO car\n" +
                    "(model, make, id_engine,price)\n" +
                    "VALUES('BMW3','2010-01-01',1,99000);", Statement.RETURN_GENERATED_KEYS);
            //retrieve generated id from database
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id=generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("No id obtained. ");
                }
            }
            System.out.println("Inserted successfully!");
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                System.out.println("Close");
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //check
        car = dao.getCarById(id);
        Assert.assertNotNull(car);
        System.out.println(car);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = "2010-01-01";
        Date date = sdf.parse(dateAsString);
        //car spec
        Assert.assertEquals(id, car.getId());
        Assert.assertEquals("BMW3", car.getModel());
        Assert.assertEquals(date, car.getMake());
        Assert.assertEquals(99000, car.getPrice());
        //engine spec
        Assert.assertEquals(1, car.getEngine().getId());
        Assert.assertEquals(2.2, car.getEngine().getDisplacement(),0.001);
        Assert.assertEquals(90, car.getEngine().getPower());

        //delete inserted car from database
        try {
            cn = ConnectorDB.getConnection();
            pst = cn.prepareStatement("DELETE FROM car c\n" +
                    "WHERE c.id=?;");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Deleted");
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                System.out.println("Close");
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getEngineById()  {

        Connection cn = null;
        Statement st = null;
        PreparedStatement pst = null;
        int id=0;

        //insert engine into database
        try {
            cn = ConnectorDB.getConnection();
            st=cn.createStatement();
            st.executeUpdate("INSERT INTO engine\n" +
                    "(displacement, power)\n" +
                    "VALUES (2.6,150);", Statement.RETURN_GENERATED_KEYS);
            //retrieve generated id from database
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id=generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("No id obtained. ");
                }
            }
            System.out.println("Inserted successfully!");
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                System.out.println("Close");
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //check
        engine=dao.getEngineById(id);
        Assert.assertNotNull(engine);
        System.out.println(engine);
        Assert.assertEquals(id, engine.getId());
        Assert.assertEquals(2.6, engine.getDisplacement(),0.001);
        Assert.assertEquals(150, engine.getPower());

        //delete inserted engine from database
        try {
            cn = ConnectorDB.getConnection();
            pst = cn.prepareStatement("DELETE FROM engine\n" +
                    "where id=?;");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Deleted");
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                System.out.println("Close");
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void insertCar() throws ParseException {

        Connection cn = null;
        PreparedStatement pst = null;

        engine.setId(1);
        car.setModel("Volvo");
        car.setMake(new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-11"));
        car.setPrice(155000);
        car.setEngine(engine);
        //insert car and get id from database
        Car c=dao.insertCar(car);
        int id_fromDB=c.getId();
        Assert.assertNotNull(id_fromDB);

        //get inserted car from database
        Car selectedCar=new Car();
        Engine selectedEngine=new Engine();
        try {
            cn = ConnectorDB.getConnection();
            pst =cn.prepareStatement("SELECT c.id,c.model,c.make,c.price,e.id,e.displacement,e.power\n" +
                    "FROM car c \n" +
                    "INNER JOIN engine e \n" +
                    "ON c.id_engine=e.id \n" +
                    "WHERE c.id=?");
            pst.setInt(1, id_fromDB);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                selectedCar.setId(id_fromDB);
                selectedCar.setModel(resultSet.getString(2));
                selectedCar.setMake(resultSet.getDate(3));
                selectedCar.setPrice(resultSet.getInt(4));
                //get and put the engine in our car
                selectedEngine.setId(resultSet.getInt(5));
                selectedEngine.setDisplacement(resultSet.getDouble(6));
                selectedEngine.setPower(resultSet.getInt(7));
                selectedCar.setEngine(selectedEngine);
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                System.out.println("Close");
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println(selectedCar);
        Assert.assertEquals(id_fromDB, selectedCar.getId());
        Assert.assertEquals("Volvo", selectedCar.getModel());
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-11"), selectedCar.getMake());
        Assert.assertEquals(1, selectedCar.getEngine().getId());
        Assert.assertEquals(155000, selectedCar.getPrice());

        //delete inserted car from database
        try {
            cn = ConnectorDB.getConnection();
            pst = cn.prepareStatement("DELETE FROM car c\n" +
                    "WHERE c.id=?;");
            pst.setInt(1, id_fromDB);
            pst.executeUpdate();
            System.out.println("Deleted");
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                System.out.println("Close");
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void insertEngine() {

        Connection cn = null;
        PreparedStatement pst = null;

        engine.setPower(40);
        engine.setDisplacement(0.7);

        //insert engine and get id from database
        Engine e=dao.insertEngine(engine);
        int id_fromDB=e.getId();
        Assert.assertNotNull(id_fromDB);

        //get inserted engine from database
        Engine selectedEngine=new Engine();
        try {
            cn = ConnectorDB.getConnection();
            pst =cn.prepareStatement("SELECT e.id,e.displacement,e.power\n" +
                    "FROM engine e  \n" +
                    "where e.id=?;");
            pst.setInt(1, id_fromDB);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                selectedEngine.setId(id_fromDB);
                selectedEngine.setDisplacement(resultSet.getDouble(2));
                selectedEngine.setPower(resultSet.getInt(3));
            }

        } catch (SQLException ex) {
            System.err.println("SQL exception (request or table failed): " + ex);
        } finally {
            try {
                System.out.println("Close");
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException exep) {
                exep.printStackTrace();
            }
        }


        Assert.assertEquals(id_fromDB, selectedEngine.getId());
        Assert.assertEquals(40, selectedEngine.getPower());
        Assert.assertEquals(0.7, selectedEngine.getDisplacement(),0.001);

        //delete inserted engine from database
        try {
            cn = ConnectorDB.getConnection();
            pst = cn.prepareStatement("DELETE FROM engine\n" +
                    "WHERE id=?;");
            pst.setInt(1, id_fromDB);
            pst.executeUpdate();
            System.out.println("Deleted");
        } catch (SQLException exe) {
            System.err.println("SQL exception (request or table failed): " + exe);
        } finally {
            try {
                System.out.println("Close");
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

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
