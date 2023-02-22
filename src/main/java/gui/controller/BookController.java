package gui.controller;

import gui.restclient.ReservationServiceRestClient;
import gui.restclient.dto.AccommodationDto;
import gui.restclient.dto.ReservationDto;
import gui.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class BookController implements EventHandler<ActionEvent> {

    private ReservationView reservationView;
    private ReservationServiceRestClient reservationServiceRestClient;

    public BookController(ReservationView reservationView) {
        this.reservationView = reservationView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        AccommodationDto selected = reservationView.getTableReservation().getSelectionModel().getSelectedItem();
        try {
            ReservationDto reservation = reservationServiceRestClient.makeReservation(selected.getVehicle(), selected.getVehicleType(), selected.getStartDate(), selected.getEndDate());
            reservationView.getReservationList().add(reservation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
