package HW6.dao.Impl;

import HW6.dao.ServiceStationsDAO;
import HW6.model.ServiceStations;
import HW6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;


public class ServiceStationsDAOImpl implements ServiceStationsDAO {

    public SessionFactory factory=HibernateUtil.getSessionFactory();

    public ServiceStations addStation(ServiceStations station) throws SQLException {
        try(Session session= factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(station);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return station;
    }

    public void updateStation(ServiceStations station) throws SQLException {
        try(//SessionFactory factory=HibernateUtil.getSessionFactory();
            Session session= factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(station);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ServiceStations getStationById(Long station_id) throws SQLException {
        ServiceStations station = null;
        try(//SessionFactory factory=HibernateUtil.getSessionFactory();
            Session session= factory.openSession()) {
            station = session.get(ServiceStations.class, station_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return station;
    }

    public void deleteStation(ServiceStations station) throws SQLException {
        try(//SessionFactory factory=HibernateUtil.getSessionFactory();
            Session session= factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(station);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
