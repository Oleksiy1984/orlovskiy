package HW4.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Alexey.
 */
public class Server {

    private Queue<Connection> connections =
            new LinkedBlockingQueue<>(10);
    private ServerSocket server;


      //Конструктор создаёт сервер. Затем для каждого подключения создаётся
      //объект Connection и добавляет его в список подключений.
    public Server() {
        try {
            server = new ServerSocket(5555);

            while (true) {
                Socket socket = server.accept();

                // Создаём объект Connection и добавляем его в список
                Connection con = new Connection(socket);
                connections.add(con);

                // Инициализирует поток и запускает метод run(),
                // которая выполняется одновременно с остальной программой
                con.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            server.close();
                Iterator<Connection> iter = connections.iterator();
                while(iter.hasNext()) {
                    (iter.next()).close();
                }
        } catch (Exception e) {
            System.err.println("Потоки не были закрыты!");
        }
    }


     //  в методе run() получает информацию от пользователя и
    //пересылает её другим
    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;
        private String name = "";


        public Connection(Socket socket) {
            this.socket = socket;

            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

            }
            catch ( IOException e) {
                e.printStackTrace();
                close();
            }
        }

        @Override
        public void run() {
            try {
                name = in.readLine();
                // Отправляем всем клиентам сообщение о том, что зашёл новый пользователь
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        ( iter.next()).out.println(name + " cames now");
                    }

                String str;
                while (true) {
                    str = in.readLine();
                    if(str.equals("exit")) break;

                    // Отправляем всем клиентам очередное сообщение
                        Iterator<Connection> iter2 = connections.iterator();
                        while(iter2.hasNext()) {
                            ( iter2.next()).out.println(name + ": " + str);
                        }
                }

                    Iterator<Connection> iter3 = connections.iterator();
                    while(iter3.hasNext()) {
                        ( iter3.next()).out.println(name + " has left");
                    }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }


         //Закрывает входной и выходной потоки и сокет
        public void close() {
            try {
                in.close();
                out.close();
                socket.close();

                // Если больше не осталось соединений, закрываем всё, что есть и
                // завершаем работу сервера
                connections.remove(this);
                if (connections.size() == 0) {
                    Server.this.closeAll();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Потоки не были закрыты!");
            }
            finally {
                this.interrupt();
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
