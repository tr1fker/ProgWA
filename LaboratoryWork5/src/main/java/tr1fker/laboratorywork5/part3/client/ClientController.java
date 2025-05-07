package tr1fker.laboratorywork5.part3.client;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private boolean serverConnected;

    private Thread serverThread;

    @FXML
    private ListView<String> listResult;

    @FXML
    private TextField iPAddressField;

    @FXML
    private TextField portField;

    @FXML
    private TextField inputField;


    @FXML
    public void sendOnMouseClicked(MouseEvent event) {
        if (this.serverConnected) this.out.println(this.inputField.getText());
        else this.listResult.getItems().add(0, "Подключитесь к серверу!");
    }

    @FXML
    public void connectOnMouseClicked(MouseEvent event) {
        this.serverConnected = false;

        final String iPAddress = iPAddressField.getText();
        final int port = Integer.parseInt(portField.getText());
        System.out.println(iPAddress + ":" + port);

        try {
            this.socket = new Socket(iPAddress, port);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.serverConnected = true;

            this.listResult.getItems().add(0, "Успешно подключено к серверу " + iPAddress + ":" + port);

            serverThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        String finalResponse = response;
                        javafx.application.Platform.runLater(() -> listResult.getItems().add(0, "Сервер: " + finalResponse));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            serverThread.setDaemon(true);
            serverThread.start();
        } catch (Exception e) {
            this.listResult.getItems().add(0, "Не удалось подключиться!");
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        this.serverConnected = false;
    }
}