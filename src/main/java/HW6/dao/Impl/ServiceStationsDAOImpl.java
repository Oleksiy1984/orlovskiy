package HW6.dao.Impl;

import HW6.dao.ServiceStationsDAO;
import HW6.model.ServiceStations;
import HW6.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;


public class ServiceStationsDAOImpl implements ServiceStationsDAO {

    public void addStation(ServiceStations station) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(station);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateStation(ServiceStations station) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(station);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public ServiceStations getStationById(Long station_id) throws SQLException {
        Session session = null;
        ServiceStations station = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            station = session.get(ServiceStations.class, station_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return station;
    }

    public void deleteStation(ServiceStations station) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(station);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
