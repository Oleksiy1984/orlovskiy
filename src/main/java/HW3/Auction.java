package HW3;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexey.
 */
public class Auction {

    private static final CountDownLatch START = new CountDownLatch(16);
    private static int boughtCount = 0;

    public static void main(String[] args) throws InterruptedException {

        List<Runnable> list = new ArrayList();
        for(int i=1;i<11;i++){
            list.add(new Buyer(i));
        }
        for(int i=1;i<6;i++){
            list.add(new Car(i));
        }
        Collections.shuffle(list);
        ExecutorService executor = Executors.newFixedThreadPool(15);
        for(Runnable run: list) {
            executor.submit(run);
            executor.awaitTermination(1, TimeUnit.SECONDS);
        }


        Thread.sleep(2000);
        System.out.println("В саллоне 5 автомобилей, возле магазина 10 покупателей.");
        System.out.println("Аукцион начался!");
        START.countDown();
        Thread.sleep(1000);
        executor.shutdown();

    }

    private static class Car implements Runnable {
        private int carNumber;

        Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.println(carNumber+" автомобиль появился на аукционе!");
                START.countDown();
                START.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Buyer implements Runnable {
        private int buyerNumber;


        Buyer(int buyerNumber) {
            this.buyerNumber = buyerNumber;
        }

        @Override
        public void run() {
            try {
                System.out.println("Покупатель № " + buyerNumber + " ждет аукциона.");
                START.countDown();
                START.await();
                while (boughtCount < 5) {
                    boughtCount++;
                    Thread.sleep(new Random().nextInt(2000));
                    System.out.println("Покупатель № " + buyerNumber + " купил автомобиль!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}