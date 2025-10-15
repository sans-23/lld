package lld.problems.parkingLot;

import java.util.List;

public class ParkingSpotManager {
    List<ParkingSpot> parkingSpots;

    public ParkingSpotManager(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public ParkingSpot findParkingSpot(){
        for(ParkingSpot spot : parkingSpots){
            if(spot.isAvailable()){
                return spot;
            }
        }
        return null;
    }

    public void freeParkingSpot(ParkingSpot spot){
        spot.removeVehicle();
    }
}
