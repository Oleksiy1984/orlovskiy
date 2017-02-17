package HW3.FourThreads.HighLevelConcurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Alexey.
 */
public class Threads {

    public static void main(String[] args) throws InterruptedException {

        Phaser phaser = new Phaser(1);
        int phase;

        for (int i = 0; i < 3; i++) {
            switch (i){
                case 0:{//фаза 0
                    new Thread(new Worker(1, phaser)).start();
                    // ждем завершения фазы 0
                    phase = phaser.getPhase();
                    phaser.arriveAndAwaitAdvance();//указывает что поток завершил выполнение фазы
                    System.out.println("Фаза " + phase + " завершена\n");
                    break;
                }
                case 1:{//фаза 1
                    new Thread(new Worker(2, phaser)).start();
                    new Thread(new Worker(3, phaser)).start();
                    // ждем завершения фазы 1
                    phase = phaser.getPhase();
                    phaser.arriveAndAwaitAdvance();
                    System.out.println("Фаза " + phase + " завершена\n");
                    break;
                }
                case 2:{//фаза 2
                    new Thread(new Worker(4, phaser)).start();
                    // ждем завершения фазы 2
                    phase = phaser.getPhase();
                    phaser.arriveAndAwaitAdvance();
                    System.out.println("Фаза " + phase + " завершена\n");
                    break;
                }
            }
        }
        phaser.arriveAndDeregister();
        }

    public static class Worker implements Runnable {
        private int number;
        Phaser phaser;

        public Worker(int number, Phaser phaser) {
            this.number = number;
            this.phaser = phaser;
            phaser.register();
        }

        @Override
        public void run() {
            try {
                System.out.println(number + "  started... и выполняет фазу " + phaser.getPhase());
                Random rand = new Random();
                long  n = (long) rand.nextInt(4000) + 1000;
                    Thread.sleep(n);
                System.out.println(number+" finished!");
                phaser.arriveAndDeregister();// сообщаем о завершении фаз и удаляем с регистрации объекты
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}