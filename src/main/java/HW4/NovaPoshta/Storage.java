package HW4.NovaPoshta;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Alexey.
 */
public class Storage implements Iterable<Parcel> {

    private Queue<Parcel> parcels = null;
    private String name;



    public Storage (int capacity, List<Parcel> newParcels, String name) {
        this.name=name;
        this.parcels=  new LinkedBlockingQueue<>(capacity);
        parcels.addAll(newParcels);
    }
    public String getName() {
        return name;
    }
    public Parcel getParcel() {
        return parcels.poll();
    }
    public boolean setParcel(Parcel p) {
        return parcels.add(p);
    }
    @Override
    public Iterator<Parcel> iterator() {
        return parcels.iterator();
    }
}

