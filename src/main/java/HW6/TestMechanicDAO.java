package HW6;

import HW6.dao.Impl.MechanicDAOImpl;
import HW6.dao.Impl.ServiceStationsDAOImpl;
import HW6.dao.MechanicDAO;
import HW6.dao.ServiceStationsDAO;
import HW6.model.Mechanic;
import HW6.model.ServiceStations;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author Alexey.
 */
public class TestMechanicDAO {

    private Mechanic mechanic=null;
    private MechanicDAOImpl mechDAO=null;
    private ServiceStationsDAOImpl dao=null;
    private ServiceStations station;

    @Before
    public void setUp() throws Exception {
        dao= new ServiceStationsDAOImpl();
        station=dao.getStationById(1L);

        mechDAO = new MechanicDAOImpl();
        mechanic = Mechanic.builder().name("Ron").surname("Ostling")
                .station(station).build();

    }

    @After
    public void cleanup() throws Exception {
        dao.factory.close();
        mechDAO.factory.close();
        mechDAO=null;
        mechanic=null;
    }

    @Test
    public void testAddMechanic() throws SQLException {

        //insert
        Mechanic insertedMechanic = mechDAO.addMechanic(mechanic);
        Assert.assertNotNull(insertedMechanic.getId());
        mechanic.setId(insertedMechanic.getId());
        Assert.assertEquals(mechanic, insertedMechanic);
        System.out.println(mechanic);
        System.out.println(station);
    }

    @Test
    public void testDeleteMechanic() throws SQLException {

        Mechanic m=mechDAO.getMechanicById(13L);
        mechDAO.deleteMechanic(m);
    }

}
