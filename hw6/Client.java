package hw6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket sock;
    private Scanner in;
    private PrintWriter out;

    private ClientWindow clientWindow = new ClientWindow(this);

    Client(){
        initClient();
        startThread();
    }
    // создание клиента и обработчиков потоков
    private void initClient(){
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // создание потока прослушивания входящих данных
    private void startThread(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true){
                        if (in.hasNext()){
                            String w = in.nextLine();
                            if (w.equalsIgnoreCase("end")){
                                clientWindow.appendTxtA("Сервер завершил сеанс связи" + "\n");
                                out.close();
                                in.close();
                                break;
                            }
                            clientWindow.appendTxtA(w + "\n");
                        }
                    }
                } catch (Exception e){ }
            }
        }).start();
    }
    // метод закрытия клиента для графического интерфейса
    protected void close(){
        try {
            out.println("end");
            out.flush();
            sock.close();
            out.close();
            in.close();
        } catch (IOException e){ }
    }
    // метод отправки сообщения клиентом
    protected void sendMsg(){
        out.println(clientWindow.getTxtF());
        out.flush();
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
