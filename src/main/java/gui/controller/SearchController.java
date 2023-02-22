package gui.controller;

import gui.restclient.ReservationServiceRestClient;
import gui.restclient.dto.AccommodationListDto;
import gui.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URISyntaxException;

public class SearchController implements EventHandler<ActionEvent> {

    private ReservationView reservationView;
    private ReservationServiceRestClient reservationServiceRestClient;

    public SearchController(ReservationView reservationView) {
        this.reservationView = reservationView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            reservationView.getAccommodationList().clear();
            AccommodationListDto accommodationList = reservationServiceRestClient.getAvailable(reservationView.getTfCity().getText(),
                    reservationView.getTfHotel().getText(), reservationView.getTfFrom().getText(), reservationView.getTfTo().getText());
            reservationView.getAccommodationList().addAll(accommodationList.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
