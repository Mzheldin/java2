package hw7;

public interface Controller {
    void sendMessage(String msg);

    void closeConnection();

    void showUI(ClientUi clientUI);
}