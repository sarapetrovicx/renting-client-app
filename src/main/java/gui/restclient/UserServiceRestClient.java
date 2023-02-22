package gui.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.ClientApp;
import gui.model.User;
import gui.restclient.dto.ClientCreateDto;
import gui.restclient.dto.ClientDto;
import gui.restclient.dto.TokenRequestDto;
import gui.restclient.dto.TokenResponseDto;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;

public class UserServiceRestClient {
//    public static final String URL = "http://localhost:8084/userservice/api";
    public static final String URL = "http://localhost:8080/api/client";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public String login(String email, String password) throws IOException {

        TokenRequestDto tokenRequestDto = new TokenRequestDto(email, password);

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

        Request request = new Request.Builder()
                .url(URL + "/login")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response + " jee");

        if (response.code() == 200) {
            String json = response.body().string();
            TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

            return dto.getToken();
        } else{
            throw new RuntimeException("Invalid username or password");
        }
    }

    public ClientDto registerClient(ClientCreateDto clientCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        System.out.println(clientCreateDto.getUsername());
        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() == 201) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);

        } else {
            throw new RuntimeException("Something went wrong");
        }
    }


    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    public ClientDto editClient(ClientCreateDto clientCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

        Request request = new Request.Builder()
                .url(URL + "/edit/" + getUser(ClientApp.getInstance().getToken()).getId())
                .put(body)
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code() + " edit");
        if (response.code() == 201 || response.code() == 200  || response.code() == 202) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }
}
