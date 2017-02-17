package HW3.WaitNotifyAllLatch;


/**
 * @author Alexey.
 */
public class Store {

    //Создаем Latch на 4 условия
    private static final Latch latch = new Latch(4);


    public static void main(String[] args) throws InterruptedException {

        System.out.println("WaitNotifyAll Latch");
        for (int i = 1; i <=3; i++) {
            new Thread(new Human(i)).start();
            Thread.sleep(1000);
        }

        while (latch.getCount() > 3){ //Проверяем, собрались ли все люди
            Thread.sleep(100); }             //у магазина. Если нет, ждем 100ms

        Thread.sleep(1000);
        System.out.println("Заходите!");
        Thread.sleep(1000);
        //Команда дана, уменьшаем счетчик на 1
        //счетчик становится равным нулю, и все ожидающие потоки
        //одновременно разблокируются
        latch.countDown();

    }

    public static class Human implements Runnable {

        private int humanNubmer;


        public Human(int humanNubmer) throws InterruptedException {
            this.humanNubmer = humanNubmer;

        }

        @Override
        public void run() {
            try {
                System.out.printf("%d человек подошел к магазину.\n", humanNubmer);
                //Человек подошел к магазину - условие выполнено
                //уменьшаем счетчик на 1
                latch.countDown();
                //метод awaitZero() блокирует поток, вызвавший его, до тех пор, пока
                //счетчик CountDownLatch не станет равен 0
                latch.awaitZero();
                System.out.printf("%d человек зашел!\n", humanNubmer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

