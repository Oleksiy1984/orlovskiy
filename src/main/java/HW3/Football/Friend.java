package HW3.Football;

import static HW3.Football.Football.START;

/**
 * @author Alexey.
 */
public class Friend implements Runnable{
    private int numberFriend;

    Friend(int numberFriend) {
        this.numberFriend = numberFriend;
    }

    @Override
    public void run() {
        System.out.printf("%d друг  пришел на игру.\n", numberFriend);

        try {
           START.countDown();//уменьшаем счетчик
            START.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
