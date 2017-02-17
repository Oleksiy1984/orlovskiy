package HW3;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Alexey.
 */
public class Task2 {

    public static void main(String[] args) {

        Storage storage=new Storage();
        System.out.printf("На складе %d кг яблок\n",storage.getProduct());
        Producer producer = new Producer(storage);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(producer);
        executor.submit(new Consumer(storage,1,25));
        executor.submit(new Consumer(storage,2,25));

    }
}
// Склад
class Storage{
    private int product=400;
    ReentrantLock locker;
    Condition condition;

    public int getProduct() {
        return product;
    }

    Storage(){
        locker = new ReentrantLock(true); // создаем блокировку
        condition = locker.newCondition(); // получаем условие, связанное с блокировкой
    }

    public void get(int id,int amount) {

        try{
            locker.lock();
            // пока нет доступных товаров на складе, ожидаем
            while (product<=50){

                condition.await();}
                Thread.sleep(1000);
            product=product-amount;
            System.out.printf("Магазин №%d купил %d кг\n",id,amount);
            System.out.println("Яблок на складе: " + product);

            // сигнализируем
            condition.signalAll();
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock();
        }
    }
    public void put() {

        try{
            locker.lock();
            // как только на складе меньше 50 кг яблок, поставщик их привозит
            while (product>50){
                condition.await();}
            System.out.println("Магазины ожидает товар на складе!");
            Thread.sleep(4000);
            product=product+200;
            System.out.println("Поставщик добавил 200 кг яблок");
            System.out.println("Яблок на складе: " + product);
            // сигнализируем
            condition.signalAll();
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock();
        }
    }
}
// класс Поставщик
class Producer implements Runnable{

    Storage storage;

    Producer(Storage storage){
        this.storage=storage;
    }


    public void run(){

        while(true) {
            storage.put();
        }
    }
}
// Магазин
class Consumer implements Runnable {


    Storage storage;
    private int id;
    private int amount;

    public Consumer(Storage storage, int id, int amount) {
        this.storage = storage;
        this.id = id;
        this.amount = amount;
    }

    public void run() {
        while (true) {

            if(id==2){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.get(id, amount);
        }

    }
}


