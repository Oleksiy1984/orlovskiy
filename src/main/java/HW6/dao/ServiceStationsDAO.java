package HW6.dao;

import HW6.model.ServiceStations;
import java.sql.SQLException;


public interface ServiceStationsDAO {

    void addStation(ServiceStations stations) throws SQLException;
    void updateStation(ServiceStations stations) throws SQLException;
    ServiceStations getStationById(Long stations_id) throws SQLException;
    void deleteStation(ServiceStations stations) throws SQLException;

}
