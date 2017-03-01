package HW6.dao;

import HW6.model.ServiceStations;
import HW6.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.sql.SQLException;


public interface ServiceStationsDAO {
    SessionFactory factory= HibernateUtil.getSessionFactory();
    ServiceStations addStation(ServiceStations stations) throws SQLException;
    ServiceStations updateStation(ServiceStations stations) throws SQLException;
    ServiceStations getStationById(Long stations_id) throws SQLException;
    void deleteStation(ServiceStations stations) throws SQLException;

}
