package lld.problems.parkingLot;

public abstract class ParkingSpot {
    private String v_id;
    private String parkingSpotId;
    private ParkingSpotType type;

    public ParkingSpot(String parkingSpotId, ParkingSpotType type) {
        this.parkingSpotId = parkingSpotId;
        this.type = type;
    }

    public boolean isAvailable(){
        return v_id==null;
    }

    public void assignVehicle(String v_id) {
        this.v_id = v_id;
    }

    public void removeVehicle() {
        this.v_id = null;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public ParkingSpotType getSpotType(){
        return type;
    }

    public abstract PricingStrategy getPricingStrategy();
}
