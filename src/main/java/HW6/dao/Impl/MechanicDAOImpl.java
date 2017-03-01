package HW6.dao.Impl;

import HW6.dao.MechanicDAO;
import HW6.model.Mechanic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;

/**
 * @author Alexey.
 */
public class MechanicDAOImpl implements MechanicDAO {


    public Mechanic addMechanic(Mechanic mechanic) throws SQLException {

        try(Session session= factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(mechanic);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  mechanic;
    }

    public Mechanic updateMechanic(Mechanic mechanic) throws SQLException {
        try(Session session= factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(mechanic);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mechanic;
    }


    public Mechanic getMechanicById(Long mechanic_id) throws SQLException {
        Mechanic mechanic = null;
        try(Session session= factory.openSession()) {
            mechanic = session.get(Mechanic.class, mechanic_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mechanic;
    }

    public void deleteMechanic(Mechanic mechanic) throws SQLException {
        try(Session session= factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(mechanic);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
