package HW4.NovaPoshta;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Phaser;

/**
 * @author Alexey.
 */
public class Truck implements Runnable {
    private Phaser phaser;
    private int number;
    private Storage storafeFrom;
    private Storage storageTo;
    private Queue<Parcel> bodyStorage;

    public Truck(Phaser phaser, int name, Storage stFrom,
                 Storage stTo) {
        this.phaser = phaser;
        this.number = name;
        this.bodyStorage = new ArrayDeque<>(1);
        this.storafeFrom = stFrom;
        this.storageTo = stTo;
        this.phaser.register();
    }

    public void run() {
        loadTruck();
        phaser.arriveAndAwaitAdvance();
        goTruck();
        phaser.arriveAndAwaitAdvance();
        unloadTruck();
        phaser.arriveAndDeregister();
    }

    private void loadTruck() {

            Parcel g = storafeFrom.getParcel();
            if (g == null) { // если в хранилище больше нет товара, загрузка прерывается
                return;
            }
            bodyStorage.add(g);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            System.out.println("Грузовик " + number + " загрузил товар №"
                    + g.getId());


    }

    private void unloadTruck() {

        int size = bodyStorage.size();
        for (int i = 0; i < size; i++) {//разгрузка только загруженных грузовиков
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Parcel g = bodyStorage.poll();
            storageTo.setParcel(g);
            System.out.println("Грузовик " + number + " разгрузил товар №"
                    + g.getId());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void goTruck() {

        if (bodyStorage.isEmpty()) { // если грузовик пустой, то он никуда не едет
            return;
        }
            if (storafeFrom.getName().equals("A")) {
                try {
                    Thread.sleep(500);
                    System.out.println("Грузовик " + number + " начал поездку.");
                    Thread.sleep(1000);
                    System.out.println("Грузовик " + number + " завершил поездку.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(500);
                    System.out.println("Грузовик " + number + " начал поездку.");
                    Thread.sleep(3000);
                    System.out.println("Грузовик " + number + " завершил поездку.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}

