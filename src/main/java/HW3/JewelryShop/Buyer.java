package HW3.JewelryShop;

import static HW3.JewelryShop.JewelryShop.SEMAPHORE;
import static HW3.JewelryShop.JewelryShop.SHOP_PLACES;


/**
 * @author Alexey.
 */
public class Buyer implements Runnable{
    private int buyerNumber;

    public Buyer(int buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    @Override
    public void run() {

        try {
            System.out.printf("Покупатель №%d подошел.\n", buyerNumber);
            //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
            //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
            //пока семафор не разрешит доступ
            SEMAPHORE.acquire();
            int parkingNumber = -1;

            synchronized (SHOP_PLACES) {
                for (int i = 0; i < 5; i++)
                    if (!SHOP_PLACES[i]) {      //Если место свободно
                        SHOP_PLACES[i] = true;  //занимаем его
                        parkingNumber = i;
                        System.out.printf("Покупатель №%d зашел.\n", buyerNumber);
                        System.out.println("Покупателей " + (5 - SEMAPHORE.availablePermits()));
                        break;
                    }
            }

            Thread.sleep((int) (Math.random() * 7000 + 1000));       //Покупки

            synchronized (SHOP_PLACES) {
                SHOP_PLACES[parkingNumber] = false;//Освобождаем место
            }

            //освобождаем ресурс
            SEMAPHORE.release();
            System.out.printf("Покупатель №%d вышел.\n", buyerNumber);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
