package HW3.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Alexey.
 */
public class Store {
    //Создаем CountDownLatch на 4 условия
    private static final CountDownLatch START = new CountDownLatch(4);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 3; i++) {
            new Thread(new Human(i)).start();
            Thread.sleep(1000);
        }

        while (START.getCount() > 3) //Проверяем, собрались ли все люди
            Thread.sleep(100);              //у магазина. Если нет, ждем 100ms

        Thread.sleep(2000);
        System.out.println("Заходите!");
        START.countDown();//Команда дана, уменьшаем счетчик на 1
        //счетчик становится равным нулю, и все ожидающие потоки
        //одновременно разблокируются
    }

    public static class Human implements Runnable {
        private int humanNubmer;


        public Human(int humanNubmer) {
            this.humanNubmer = humanNubmer;

        }

        @Override
        public void run() {
            try {
                System.out.printf("%d человек подошел к магазину.\n", humanNubmer);
                //Человек подошел к магазину - условие выполнено
                //уменьшаем счетчик на 1
                START.countDown();
                //метод await() блокирует поток, вызвавший его, до тех пор, пока
                //счетчик CountDownLatch не станет равен 0
                START.await();

                System.out.printf("%d человек зашел!\n", humanNubmer);
            } catch (InterruptedException e) {
            }
        }
    }
}
