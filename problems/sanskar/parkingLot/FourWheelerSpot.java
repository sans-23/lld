package lld.problems.sanskar.parkingLot;

public class FourWheelerSpot extends ParkingSpot{
    private PricingStrategy ps;

    public FourWheelerSpot(String parkingSpotId, PricingStrategy ps){
        super(parkingSpotId, ParkingSpotType.FOUR_WHEELER_SPOT);
        this.ps = ps;
    }

    @Override
    public PricingStrategy getPricingStrategy() {
        return ps;
    }
}
