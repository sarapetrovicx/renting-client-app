package gui.controller;

import gui.restclient.dto.ClientCreateDto;
import gui.restclient.dto.ClientDto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import gui.restclient.UserServiceRestClient;
import gui.view.RegisterClientView;

import java.io.IOException;
import java.sql.Date;

public class RegisterClientController implements EventHandler<ActionEvent> {

    private RegisterClientView registerClientView;
    private UserServiceRestClient userServiceRestClient;

    public RegisterClientController(RegisterClientView registerClientView){
        this.registerClientView = registerClientView;
        this.userServiceRestClient = new UserServiceRestClient();
    }
    @Override
    public void handle(ActionEvent event) {
        ClientCreateDto clientCreateDto = new ClientCreateDto();
        clientCreateDto.setFirstName(registerClientView.getTfFirstName().getText());
        clientCreateDto.setLastName(registerClientView.getTfLastName().getText());
        clientCreateDto.setEmail(registerClientView.getTfEmail().getText());
        clientCreateDto.setUsername(registerClientView.getTfUsername().getText());
        clientCreateDto.setPassword(registerClientView.getTfPassword().getText());
//        clientCreateDto.setBirthDate(Date.valueOf(registerClientView.getTfBirth().getText()));
//        clientCreateDto.setPhoneNum(registerClientView.getTfPhone().getText());
        clientCreateDto.setPassportNum(registerClientView.getTfPassport().getText());

        try {
            ClientDto client = userServiceRestClient.registerClient(clientCreateDto);
            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
