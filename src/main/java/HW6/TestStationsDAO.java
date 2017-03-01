package HW6;

import HW6.dao.Impl.ServiceStationsDAOImpl;
import HW6.dao.ServiceStationsDAO;
import HW6.model.ServiceStations;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;


/**
 * @author Alexey.
 */
public class TestStationsDAO {

    private ServiceStations station=null;
    private ServiceStationsDAO stationDAO=null;
    private Transaction tx=null;
    private Session session=null;


    @Before
    public void setUp() throws Exception {

        stationDAO=new ServiceStationsDAOImpl();
        station = ServiceStations.builder().address("Kiev").build();
    }

    @After
    public void cleanup() throws Exception {
        session.close();
        stationDAO.factory.close();
        station=null;
        stationDAO=null;
    }

    @Test
    public void testAddStation() throws SQLException {

        //insert
        ServiceStations insertedStation = stationDAO.addStation(station);
        Assert.assertNotNull(insertedStation.getId());
        Assert.assertEquals(station, insertedStation);
        //delete
        session= stationDAO.factory.openSession();
        tx = session.beginTransaction();
        session.delete(station);
        tx.commit();
    }

    @Test
    public void testGetStation() throws SQLException {
        //insert
        session = stationDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(station);
        tx.commit();
        session.close();
        //get
        ServiceStations selectedStation=stationDAO.getStationById(station.getId());
        Assert.assertNotNull(selectedStation.getId());
        Assert.assertEquals(station, selectedStation);
        //delete
        session= stationDAO.factory.openSession();
        tx = session.beginTransaction();
        session.delete(station);
        tx.commit();
    }

    @Test
    public void testUpdateStation() throws SQLException {
        //insert
        session = stationDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(station);
        tx.commit();
        session.close();
        //get
        station.setAddress("Rivne");
        ServiceStations updatedStation=stationDAO.updateStation(station);
        Assert.assertEquals(station, updatedStation);
        //delete
        session= stationDAO.factory.openSession();
        tx = session.beginTransaction();
        session.delete(station);
        tx.commit();
    }

    @Test
    public void testDeleteStation() throws SQLException {
        //insert
        session = stationDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(station);
        tx.commit();
        session.close();
        //delete
        stationDAO.deleteStation(station);
        //try to get station
        session = stationDAO.factory.openSession();
        tx = session.beginTransaction();
        ServiceStations selectedStation = session.get(ServiceStations.class, station.getId());
        tx.commit();
        Assert.assertNull(selectedStation);
    }
}
