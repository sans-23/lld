package lld.problems.parkingLot;

public class TwoWheelerSpot extends ParkingSpot{
    PricingStrategy ps;
    
    public TwoWheelerSpot(String parkingSpotId, PricingStrategy ps){
        super(parkingSpotId, ParkingSpotType.TWO_WHEELER_SPOT);
        this.ps = ps;
    }

    @Override
    public PricingStrategy getPricingStrategy() {
        return ps;
    }
}
