package gui.view;

import gui.controller.RegisterClientController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegisterClientView extends GridPane {

    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblPassword;
    private Label lblUsername;
    private Label lblPassport;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfPassword;
    private TextField tfUsername;
    private TextField tfPassport;

    private Button btnRegister;

    public RegisterClientView(){
        initViewElements();
        addElements();
        addListeners();
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
    }

    private void addListeners() {
        this.btnRegister.setOnAction(new RegisterClientController(this));
    }

    private void addElements() {
        this.addRow(0, lblFirstName, tfFirstName);
        this.addRow(1, lblLastName, tfLastName);
        this.addRow(2, lblEmail, tfEmail);
        this.addRow(3, lblUsername, tfUsername);
        this.addRow(4, lblPassword, tfPassword);
        this.addRow(5, lblPassport, tfPassport);
        this.addRow(6, btnRegister);
    }

    private void initViewElements() {
        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblPassword = new Label("Password:");
        lblPassport = new Label("Passport number:");

        tfFirstName = new TextField();
        tfLastName = new TextField();
        tfEmail = new TextField();
        tfUsername = new TextField();
        tfPassword = new TextField();
        tfPassport = new TextField();

        btnRegister = new Button("Register");
    }

    public TextField getTfFirstName() {
        return tfFirstName;
    }

    public TextField getTfLastName() {
        return tfLastName;
    }

    public TextField getTfEmail() {
        return tfEmail;
    }

    public TextField getTfPassword() {
        return tfPassword;
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public TextField getTfPassport() {
        return tfPassport;
    }
}
