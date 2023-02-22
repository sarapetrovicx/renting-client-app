package gui.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.ClientApp;
import gui.model.User;
import gui.restclient.dto.*;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Base64;

public class ReservationServiceRestClient {
    public static final String URL = "http://localhost:8082/api/reservation";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public AccommodationListDto getAvailable(String city, String company, String dateFrom, String dateTo) throws IOException, URISyntaxException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String uri = UriComponentsBuilder.fromUriString(URL+"/available")
                .queryParam("city", encodeUtf8(city))
                .queryParam("company", encodeUtf8(company))
                .queryParam("dateFrom", encodeUtf8(dateFrom))
                .queryParam("dateTo", encodeUtf8(dateTo))
                .build()
                .toUriString();
        System.out.println(uri);
        Request request = new Request.Builder()
                .url(uri)
//                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, AccommodationListDto.class);
        }

        throw new RuntimeException();
    }
//    http://localhost:8082/api/reservation/available?city=Beograd&company=Kompanija&dateFrom=2023-01-20&dateTo=2023-01-31

//    http://localhost:8082/api/reservation/available?city=Beograd&company=Kompanija&dateFrom=2023-01-20&dateTo=2023-01-31
    private static String encodeUtf8(String val) throws UnsupportedEncodingException {
        return URLEncoder.encode(val, "UTF-8");
    }

    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    public ReservationDto makeReservation(String manufacturer, String type, Date startDate, Date endDate) throws IOException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        ReservationDto rd = new ReservationDto(hotel, room, startDate, endDate, getUser(ClientApp.getInstance().getToken()).getId());
        @SuppressWarnings("deprecation")
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setId(2L);
        availabilityDto.setStartDate(startDate);
        availabilityDto.setEndDate(endDate);

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(1L);
        vehicleDto.setManufacturer(manufacturer);

        ReservationCreateDto rd = new ReservationCreateDto();
        rd.setAvailability(availabilityDto.getId());
        rd.setUserId(getUser(ClientApp.getInstance().getToken()).getId());
        rd.setCompanyVehicleId(vehicleDto.getId());

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(rd));
        Request request = new Request.Builder()
                .url(URL)
//                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException();
    }

    public ReservationListDto getReservations() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println(URL + "/" + getUser(ClientApp.getInstance().getToken()).getId());
        Request request = new Request.Builder()
                .url(URL + '/' + getUser(ClientApp.getInstance().getToken()).getId())
//                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationListDto.class);
        }

        throw new RuntimeException();
    }
}
