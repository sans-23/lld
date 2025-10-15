package lld.problems.parkingLot;

public class TwoWheelerPricingStrategy implements PricingStrategy{
    @Override
    public int calcPrice(int time){
        return time*30;
    }
}
