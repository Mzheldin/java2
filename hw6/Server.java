package hw6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    ServerSocket serv = null;
    Socket sock = null;
    PrintWriter pwcli;
    PrintWriter pwsys;
    Scanner sccli;
    Scanner scsys;

    Server(){
        initServ();
        clientThread();
        consoleThread();
    }
    // создание сервера и подключения клиента
    private void initServ(){
        try {
            serv = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println ("Клиент подключился");
//            pwcli = new PrintWriter(sock.getOutputStream());
//            pwsys = new PrintWriter(System.out);
//            sccli = new Scanner(sock.getInputStream());
//            scsys = new Scanner(System.in);
        }catch (IOException e){
            System.out.println("Ошибка инициализации сервера");
        }
    }
    // метод завершения работы сервера
    private void closeServ(){
        try {
            sock.close();
            pwsys.close();
            pwcli.close();
            scsys.close();
            sccli.close();
            serv.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
    // поток принимающий данные от клиента в консоль
    private void clientThread(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    pwsys = new PrintWriter(System.out);
                    sccli = new Scanner(sock.getInputStream());
                } catch (IOException e){ }
                while (true){
                    if (sccli.hasNext()){
                        String strcli = sccli.nextLine();
                        if (strcli.equals("end")){
                            System.out.println("Клиент завершил сеанс связи");
                            sccli.close();
                            pwsys.close();
                            break;
                        }
                        pwsys.println(strcli);
                        pwsys.flush();
                    }
                }
            }
        }).start();
    }
    // поток отправляющий данные из консоли клиенту
    private void consoleThread(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    pwcli = new PrintWriter(sock.getOutputStream());
                    scsys = new Scanner(System.in);
                } catch (IOException e){ }
                while (true){
                    if (scsys.hasNext()){
                        String strcons = scsys.nextLine();
                        if (strcons.equals("end")){
                            pwcli.println("end");
                            pwcli.flush();
                            System.out.println("Отправка данных клиенту завершена");
                            closeServ();
//                            scsys.close();
//                            pwcli.close();
                            break;
                        }
//                        if ((strcons.equals("exit"))) {
//                            pwcli.println("end");
//                            pwcli.flush();
//                            closeServ();
//                        }
                        pwcli.println("Server :" + strcons);
                        pwcli.flush();
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
