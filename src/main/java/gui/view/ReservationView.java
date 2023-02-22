package gui.view;

import gui.Main;
import gui.controller.BookController;
import gui.controller.SearchController;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.dto.AccommodationDto;
import gui.restclient.dto.ReservationDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReservationView extends VBox {

    private HBox hBoxFilers;

    private Label lblCity;
    private Label lblHotel;
    private Label lblFrom;
    private Label lblTo;
    private Label lblReservation;
    private TextField tfCity;
    private TextField tfHotel;
    private TextField tfFrom;
    private TextField tfTo;
    private Button btnSearch;
    private Button btnBook;
    private Button btnEdit;

    private ObservableList<AccommodationDto> accommodationList;
    private TableView<AccommodationDto>  tableReservation;
    private ObservableList<ReservationDto> reservationList;
    private TableView<ReservationDto>  userReservationtable;

    private ReservationServiceRestClient reservationServiceRestClient;

    public ReservationView(){
        initViewElements();
        addElements();
        addListeners();
        initReservations();
    }

    private void initReservations() {
        reservationServiceRestClient = new ReservationServiceRestClient();
        try {
            System.out.println(reservationServiceRestClient.getReservations().getContent().get(0));
            reservationList.addAll(reservationServiceRestClient.getReservations().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        btnSearch.setOnAction(new SearchController(this));
        btnBook.setOnAction(new BookController(this));
        btnEdit.setOnAction(e->{
            Scene sc = new Scene(new EditView(), 400, 400);
            Main.editStage.setScene(sc);
            Main.editStage.show();
            Main.editStage.setTitle("Edit client");
        });
    }

    private void addElements() {
        this.hBoxFilers.getChildren().addAll(lblCity, tfCity, lblHotel, tfHotel, lblFrom, tfFrom, lblTo, tfTo, btnSearch);
        this.getChildren().addAll(hBoxFilers, tableReservation, btnBook, lblReservation, userReservationtable, btnEdit);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    private void initViewElements() {
        hBoxFilers = new HBox(5);
        lblCity = new Label("City");
        lblHotel = new Label("Company");
        lblFrom = new Label("From");
        lblTo = new Label("To");
        lblReservation = new Label("Your reservations");
        tfCity = new TextField();
        tfHotel = new TextField();
        tfFrom = new TextField();
        tfTo = new TextField();
        btnSearch = new Button("Search");
        btnBook = new Button("Book");
        btnEdit = new Button("Edit profile");
        accommodationList = FXCollections.observableArrayList();
        tableReservation = new TableView<>(accommodationList);
        reservationList = FXCollections.observableArrayList();
        userReservationtable = new TableView<>(reservationList);
        TableColumn<AccommodationDto, String> col1 = new TableColumn<>("city");
        col1.setCellValueFactory(new PropertyValueFactory<>("city"));
        TableColumn<AccommodationDto, String> col2 = new TableColumn<>("company");
        col2.setCellValueFactory(new PropertyValueFactory<>("company"));
        TableColumn<AccommodationDto, String> col3 = new TableColumn<>("vehicle");
        col3.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        TableColumn<AccommodationDto, String> col4 = new TableColumn<>("vehicleType");
        col4.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        tableReservation.getColumns().add(col1);
        tableReservation.getColumns().add(col2);
        tableReservation.getColumns().add(col3);
        tableReservation.getColumns().add(col4);


        TableColumn<ReservationDto, Long> col11 = new TableColumn<>("companyVehicleId");
        col11.setCellValueFactory(new PropertyValueFactory<>("companyVehicleId"));
        TableColumn<ReservationDto, Double> col22 = new TableColumn<>("priceWithDiscount");
        col22.setCellValueFactory(new PropertyValueFactory<>("priceWithDiscount"));
//        TableColumn<ReservationDto, Double> col33 = new TableColumn<>("From");
//        col33.setCellValueFactory(new PropertyValueFactory<>("startDate"));
//        TableColumn<ReservationDto, Double> col44 = new TableColumn<>("To");
//        col44.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        userReservationtable.getColumns().add(col11);
        userReservationtable.getColumns().add(col22);
//        userReservationtable.getColumns().add(col33);
//        userReservationtable.getColumns().add(col44);
    }

    public ObservableList<AccommodationDto> getAccommodationList() {
        return accommodationList;
    }

    public TableView<AccommodationDto> getTableReservation() {
        return tableReservation;
    }

    public ObservableList<ReservationDto> getReservationList() {
        return reservationList;
    }

    public TableView<ReservationDto> getUserReservationtable() {
        return userReservationtable;
    }

    public TextField getTfCity() {
        return tfCity;
    }

    public TextField getTfFrom() {
        return tfFrom;
    }

    public TextField getTfHotel() {
        return tfHotel;
    }

    public TextField getTfTo() {
        return tfTo;
    }
}
