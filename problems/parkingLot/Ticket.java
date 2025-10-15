package lld.problems.parkingLot;

import java.time.LocalDateTime;

public class Ticket {
    private final String vehicleId;
    private final LocalDateTime timestamp;
    private final ParkingSpot parkingSpot;

    public Ticket(String vehicleId, LocalDateTime timestamp, ParkingSpot parkingSpot){
        this.vehicleId = vehicleId;
        this.timestamp = timestamp;
        this.parkingSpot = parkingSpot;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
