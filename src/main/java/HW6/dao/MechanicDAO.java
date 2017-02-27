package HW6.dao;

import HW6.model.Mechanic;
import java.sql.SQLException;


public interface MechanicDAO {
    Mechanic addMechanic(Mechanic mechanic) throws SQLException;
    void updateMechanic(Mechanic mechanic) throws SQLException;
    Mechanic getMechanicById(Long mechanic_id) throws SQLException;
    void deleteMechanic(Mechanic mechanic) throws SQLException;
}
