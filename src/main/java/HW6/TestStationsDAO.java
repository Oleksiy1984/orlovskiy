package HW6;

import HW6.dao.Impl.CarDAOImpl;
import HW6.dao.Impl.MechanicDAOImpl;
import HW6.dao.Impl.ServiceStationsDAOImpl;
import HW6.dao.ServiceStationsDAO;
import HW6.model.Car;
import HW6.model.Mechanic;
import HW6.model.ServiceStations;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexey.
 */
public class TestStationsDAO {

    private ServiceStations station=null;
    private ServiceStationsDAOImpl stationDAO=null;
    private Car car=null;
    private CarDAOImpl carDAO=null;
    private Mechanic mechanic=null;
    private MechanicDAOImpl mechanicDAO=null;

    @Before
    public void setUp() throws Exception {

        carDAO=new CarDAOImpl();
        //mechanicDAO=new MechanicDAOImpl();
        stationDAO=new ServiceStationsDAOImpl();

        car=carDAO.getCarById(5L);
        Set<Car> setCar =new HashSet<>();
        setCar.add(car);

//        mechanic=mechanicDAO.getMechanicById(15L);
//        Set<Mechanic> mSet=new HashSet<>();
//        mSet.add(mechanic);

        station = ServiceStations.builder().address("Kiev")
                .cars(setCar).build();

//        Set<ServiceStations> stat=new HashSet<>();
//        stat.add(station);
//        car.setStations(stat);

    }

    @After
    public void cleanup() throws Exception {
        station=null;
        carDAO.factory.close();
//        mechanicDAO.factory.close();
        stationDAO.factory.close();
    }

    @Test
    public void testAddStation() throws SQLException {

        //insert
        ServiceStations insertedStation = stationDAO.addStation(station);
        Assert.assertNotNull(insertedStation.getId());
        station.setId(insertedStation.getId());
        Assert.assertEquals(station, insertedStation);
        System.out.println(station);
    }
}
