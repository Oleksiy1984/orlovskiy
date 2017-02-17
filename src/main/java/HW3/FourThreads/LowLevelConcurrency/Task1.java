package HW3.FourThreads.LowLevelConcurrency;

import java.util.Random;

/**
 * @author Alexey.
 */
public class Task1 {
    public static class Worker implements Runnable{
        private int number;

        public Worker(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            System.out.println(number+" started...");
            Random rand = new Random();
            long  n = (long) rand.nextInt(4000) + 1000;
            try {
                Thread.sleep(n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(number+" finished!");
        }
    }

    public static void main(String[] args) {

        Thread first =new Thread(new Worker(1));
        Thread second =new Thread(new Worker(2));
        Thread third =new Thread(new Worker(3));
        Thread fourth =new Thread(new Worker(4));

        first.start();
        try {
            first.join();//Waits for this thread to die
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        second.start();
        third.start();
        try {
            second.join();
            third.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fourth.start();
    }
}
