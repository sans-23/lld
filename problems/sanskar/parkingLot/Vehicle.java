package lld.problems.sanskar.parkingLot;

public abstract class Vehicle {
    private String licenceString;
    private VehicleType type;

    public Vehicle(String licenceString, VehicleType type) {
        this.licenceString = licenceString;
        this.type = type;
    }

    public String getLicenceString() {
        return licenceString;
    }

    public VehicleType getVehicleType(){
        return type;
    }
}
