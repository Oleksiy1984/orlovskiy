package HW3.Football;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.CountDownLatch;


/**
 * @author Alexey.
 */
public class Football {
    protected static CountDownLatch START;

    public static void main(String[] args) {

        while (true) {
            START = new CountDownLatch(11);
            ArrayList<Runnable> list=new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(new Friend(i+1));
            }
            Collections.shuffle(list);
            try {
                for (Runnable run:list) {
                    new Thread(run).start();
                    Thread.sleep(300);
                }
                if (new Random().nextBoolean()){
                    System.out.println("Дождя нет!");
                    START.countDown();
                    System.out.println("Матч прошел успешно. Ждем следующую игру через неделю...\n");
                    Thread.sleep(10000);
                }else{
                    System.out.println("Идет дождь. Игра перенесена на следующую неделю!\n");
                    Thread.sleep(10000);
                }

            }
             catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }




}