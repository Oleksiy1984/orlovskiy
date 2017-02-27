package HW6.dao;

import HW6.model.ServiceStations;
import org.hibernate.SessionFactory;

import java.sql.SQLException;


public interface ServiceStationsDAO {

    ServiceStations addStation(ServiceStations stations) throws SQLException;
    void updateStation(ServiceStations stations) throws SQLException;
    ServiceStations getStationById(Long stations_id) throws SQLException;
    void deleteStation(ServiceStations stations) throws SQLException;

}
