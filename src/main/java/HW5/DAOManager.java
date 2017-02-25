package HW5;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexey.
 */
public class DAOManager {

   private Connection cn = null;

    public DAOManager(Connection cn) {
        this.cn = cn;
    }

    private final static String getCarById ="SELECT c.id,c.model,c.make,c.price,e.id,e.displacement,e.power\n" +
            "FROM car c \n" +
            "INNER JOIN engine e \n" +
            "ON c.id_engine=e.id\n" +
            "WHERE c.id=?";
    private final static String getEngineById ="SELECT e.id,e.displacement,e.power,c.id,c.model,c.make,c.price\n" +
            "FROM engine e  \n" +
            "left join car c \n" +
            "on e.id=c.id_engine\n" +
            "where e.id=?;";
    private final static String insertCar="INSERT INTO car\n" +
            "(model, make, id_engine,price)\n" +
            "VALUES(?,?,?,?);";
    private final static String insertEngine="INSERT INTO engine\n" +
            "(displacement, power)\n" +
            "VALUES(?,?);";

    public Car getCarById(int id){
        Car car=new Car();
        Engine engine;
        //using try-with-resources statement
        try(PreparedStatement st =cn.prepareStatement(getCarById)) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
              engine= Engine.builder().id(resultSet.getInt(5))
                        .displacement(resultSet.getDouble(6))
                        .power(resultSet.getInt(7)).build();

               car= Car.builder().id(id).model(resultSet.getString(2))
                        .make(resultSet.getDate(3))
                        .price(resultSet.getInt(4))
                        .engine(engine).build();
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return car;
    }

    public Engine getEngineById(int id){
        Engine engine=new Engine();
        Set<Car> set=new HashSet<>();
        try(PreparedStatement st =cn.prepareStatement(getEngineById)) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                engine=Engine.builder().id(id).displacement(resultSet.getDouble(2))
                        .power(resultSet.getInt(3)).build();
                //Lets do our relationship bidirectional
                if (resultSet.getInt(4)!=0) {
                    Car car=Car.builder().id(resultSet.getInt(4))
                            .model(resultSet.getString(5))
                            .make(resultSet.getDate(6))
                            .price(resultSet.getInt(7))
                            .engine(engine).build();

                    set.add(car);
                    //adding set of cars to our one engine
                    engine.setSet(set);
                    }
                }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return engine;
    }

    public Car insertCar(Car car){
        //first check if engine exist
        if(checkEngineId(car.getEngine().getId())) {
            try(PreparedStatement st = cn.prepareStatement(insertCar, Statement.RETURN_GENERATED_KEYS)) {
                st.setString(1, car.getModel());
                st.setDate(2, new java.sql.Date(car.getMake().getTime()));
                st.setInt(3, car.getEngine().getId());
                st.setInt(4, car.getPrice());
                st.executeUpdate();
                //retrieve generated id from database
                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        car.setId(generatedKeys.getInt(1));
                    }
                    else {
                        throw new SQLException("No id obtained.");
                    }
                }
                System.out.println("Inserted successfully!");
                System.out.println(car);
            } catch (SQLException e) {
                System.err.println("SQL exception (request or table failed): " + e);
            }
        }
        else{
            System.out.println("Engine with id="+car.getEngine().getId()+" doesn't exist!");
            System.out.println("0 rows affected");
        }
        return car;
    }

    public boolean checkEngineId(int id){
        Set<Integer> q=new HashSet<>();
        try(Statement stm =cn.createStatement();) {
            ResultSet rs=stm.executeQuery("select e.id from engine e;");

            while (rs.next()) {
               q.add(rs.getInt(1));
            }
            if(q.contains(id)){
                return true;
            }
            System.out.println("Ids of available engines: ");
            System.out.println(q);

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return false;
    }

    public Engine insertEngine(Engine engine){

        try(PreparedStatement st = cn.prepareStatement(insertEngine, Statement.RETURN_GENERATED_KEYS)) {
            st.setDouble(1, engine.getDisplacement());
            st.setInt(2, engine.getPower());
            st.executeUpdate();
            //retrieve generated id from database
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    engine.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("No id obtained.");
                }
            }
            System.out.println("Inserted successfully!");
            System.out.println(engine);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return engine;
    }

}
