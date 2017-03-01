package HW6.dao;

import HW6.model.Mechanic;
import HW6.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.sql.SQLException;


public interface MechanicDAO {
    SessionFactory factory= HibernateUtil.getSessionFactory();
    Mechanic addMechanic(Mechanic mechanic) throws SQLException;
    Mechanic updateMechanic(Mechanic mechanic) throws SQLException;
    Mechanic getMechanicById(Long mechanic_id) throws SQLException;
    void deleteMechanic(Mechanic mechanic) throws SQLException;
}
