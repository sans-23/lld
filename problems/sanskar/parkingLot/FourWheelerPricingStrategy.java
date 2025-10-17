package lld.problems.sanskar.parkingLot;

public class FourWheelerPricingStrategy implements PricingStrategy{
    @Override
    public int calcPrice(int time){
        return time*30;
    }
}
