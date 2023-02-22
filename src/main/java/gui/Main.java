package gui;

import gui.view.EditView;
import gui.view.RegisterClientView;
import gui.view.ReservationView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.view.LoginView;

public class Main extends Application {

    public static Stage mainStage;
    public static Stage secondStage;
    public static Stage editStage;

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = new Stage();
        secondStage = new Stage();
        editStage = new Stage();
        Scene sc = new Scene(new LoginView(), 300, 300);
        mainStage.setTitle("SK-2");
        mainStage.setScene(sc);
        mainStage.show();
//        Scene scene = new Scene(new EditView(), 300, 300);
//        mainStage.setScene(scene);
//        mainStage.show();
    }


}
