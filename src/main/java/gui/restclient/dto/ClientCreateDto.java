package gui.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ClientCreateDto {

    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String username;
    private String password;
    @JsonProperty("passport_num")
    private String passportNum;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

}
