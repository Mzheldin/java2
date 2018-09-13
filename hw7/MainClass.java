package hw7;

public class MainClass {
    public static void main(String[] args) {
        Controller controller = new ClientController();
        ClientUi clientUI = new Client(controller);
        controller.showUI(clientUI);
    }
}
