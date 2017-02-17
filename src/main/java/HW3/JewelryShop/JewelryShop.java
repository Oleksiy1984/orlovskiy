package HW3.JewelryShop;


import java.util.concurrent.*;

/**
 *
 * @author Alexey.
 */
public class JewelryShop {


    //Место занято - true, свободно - false
    protected static final boolean[] SHOP_PLACES = new boolean[5];
    protected static final Semaphore SEMAPHORE=new Semaphore(5,true);


    public static void main(String[] args) throws InterruptedException {

        ExecutorService e=Executors.newFixedThreadPool(5);

        while (true){
            do {
                for (int i = 1; i <= 5; i++) {
                    e.submit(new Buyer(i));
                    e.awaitTermination(1100,TimeUnit.MILLISECONDS);
                }
            }
            //Если в магазине становиться меньше 4 покупателей магазин закрывается на перерыв
            while ((5 - SEMAPHORE.availablePermits()) > 3);
            System.out.println("Ребята выходим магазин закрывается!");
            e.awaitTermination(10,TimeUnit.SECONDS);//ждем пока потоки завершат свою работу
        System.out.println("Перерыв...\n");
        Thread.sleep(10000);//перерыв на 10 секунд
    }
    }
}




