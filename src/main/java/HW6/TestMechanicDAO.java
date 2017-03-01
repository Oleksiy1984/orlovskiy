package HW6;

import HW6.dao.Impl.MechanicDAOImpl;
import HW6.dao.Impl.ServiceStationsDAOImpl;
import HW6.dao.MechanicDAO;
import HW6.model.Mechanic;
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
public class TestMechanicDAO {

    private Mechanic mechanic=null;
    private MechanicDAO mechDAO=null;
    private ServiceStationsDAOImpl dao=null;
    private ServiceStations station=null;
    private Transaction tx=null;
    private Session session=null;

    @Before
    public void setUp() throws Exception {
        dao= new ServiceStationsDAOImpl();
        station=ServiceStations.builder().address("Lviv").build();
        dao.addStation(station);
        mechDAO = new MechanicDAOImpl();
        mechanic = Mechanic.builder().station(station).name("Ron").surname("Ostling")
                .build();
    }

    @After
    public void cleanup() throws Exception {
        session.close();
        dao.deleteStation(station);
        dao.factory.close();
        mechDAO.factory.close();
        dao=null;
        mechDAO=null;
        mechanic=null;
        station=null;
    }

    @Test
    public void testAddMechanic() throws SQLException {

        //insert
        Mechanic insertedMechanic = mechDAO.addMechanic(mechanic);
        Assert.assertNotNull(insertedMechanic.getId());
        Assert.assertEquals(mechanic, insertedMechanic);
        //delete
        session= mechDAO.factory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(mechanic);
            tx.commit();
    }

    @Test
    public void testUpdateMechanic() throws SQLException {
        //insert
        session= mechDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(mechanic);
        tx.commit();
        session.close();
        //update
        mechanic.setName("Andrey");
        Mechanic newMechanic = mechDAO.updateMechanic(mechanic);
        Assert.assertEquals(mechanic, newMechanic);
        //delete
        session= mechDAO.factory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(mechanic);
        tx.commit();
    }

    @Test
    public void testGetMechanic() throws SQLException {
        //insert
        session= mechDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(mechanic);
        tx.commit();
        session.close();
        //get
        Mechanic selectedMechanic = mechDAO.getMechanicById(mechanic.getId());
        Assert.assertNotNull(selectedMechanic.getId());
        Assert.assertEquals(mechanic, selectedMechanic);
        //delete
        session= mechDAO.factory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(mechanic);
        tx.commit();
    }

    @Test
    public void testDeleteMechanic() throws SQLException {
        //insert
        session= mechDAO.factory.openSession();
        tx = session.beginTransaction();
        session.save(mechanic);
        tx.commit();
        session.close();
        //delete
        mechDAO.deleteMechanic(mechanic);
        //try to get mechanic
        session = mechDAO.factory.openSession();
        tx = session.beginTransaction();
        Mechanic selectedMechanic = session.get(Mechanic.class, mechanic.getId());
        tx.commit();
        Assert.assertNull(selectedMechanic);
    }

}
