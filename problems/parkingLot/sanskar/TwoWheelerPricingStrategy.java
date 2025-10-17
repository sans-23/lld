package lld.problems.parkingLot.sanskar;

public class TwoWheelerPricingStrategy implements PricingStrategy{
    @Override
    public int calcPrice(int time){
        return time*30;
    }
}
