package gui.restclient.dto;

public class ReservationCreateDto {

    private Long userId;
    private Long companyVehicleId;
    private Long availability;


    public Long getCompanyVehicleId() {
        return companyVehicleId;
    }

    public void setCompanyVehicleId(Long companyVehicleId) {
        this.companyVehicleId = companyVehicleId;
    }

    public Long getAvailability() {
        return availability;
    }

    public void setAvailability(Long availability) {
        this.availability = availability;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
