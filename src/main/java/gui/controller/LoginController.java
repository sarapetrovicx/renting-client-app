package gui.controller;


import gui.ClientApp;
import gui.Main;
import gui.restclient.UserServiceRestClient;
import gui.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController implements EventHandler<ActionEvent> {

    private TextField email;
    private TextField password;
    private UserServiceRestClient userServiceRestClient;

    public LoginController(TextField email, TextField password) {
        this.email = email;
        this.password = password;
        userServiceRestClient = new UserServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String token = userServiceRestClient.login(email.getText(), password.getText());
            ClientApp.getInstance().setToken(token);
            Scene sc = new Scene(new ReservationView(), 800, 800);
            Main.mainStage.setScene(sc);
            Main.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
