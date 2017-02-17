package HW4.NovaPoshta;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Alexey.
 */
public class Main {
    public static void main(String[] args) {
        //5 поездок в А->Б и обратно
        int run=5;
        // создание коллекции посылок
        List<Parcel> listA=new ArrayList<>();
        listA.add(new Parcel(1));

        List<Parcel> listB=new ArrayList<>();
        for (int i = 2; i < 12; i++) {
            listB.add(new Parcel(i));
        }

        // Cклад A
        Storage storageA =new Storage(11, listA,"A");
        // Склад Б
        Storage storageB =new Storage(11, listB,"B");

        print("Посылки на складе A", storageA);
        print("Посылки на складе B", storageB);

        ExecutorService executor= Executors.newFixedThreadPool(15);

        while (run>0){
            Phaser phaser = new Phaser();
            phaser.register();
            int currentPhase;

            //5 грузовых машин на скаде А
        for (int i = 0; i < 5; i++) {
            executor.submit(new Truck(phaser, i+1, storageA, storageB));
        }
            //10 грузовых машин на скаде B
        for (int i = 5; i < 15; i++) {
            executor.submit(new Truck(phaser, i+1, storageB, storageA));
        }

        // синхронизация загрузки
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Загрузка завершена. Фаза " + currentPhase + " завершена.");
        // синхронизация поездки
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Поездка завершена. Фаза " + currentPhase + " завершена.");
        // синхронизация разгрузки
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Разгрузка завершена. Фаза " + currentPhase + " завершена.");
        phaser.arriveAndDeregister();
        if (phaser.isTerminated()) {
            System.out.println("Фазы завершены.");
        }
        print("Посылки на складе A", storageA);
        print("Посылки на складе B", storageB);

        run--;
    }
        executor.shutdown();

    }
    public static void print(String title, Storage storage) {
        System.out.print(title+": ");
        Iterator<Parcel> parcelIterator = storage.iterator();
        while (parcelIterator.hasNext()) {
            System.out.print(parcelIterator.next().getId() + " ");
        }
        System.out.println();
    }}