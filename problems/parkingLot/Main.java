package lld.problems.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing Low-Level Design of Parking Lot System");

        // Create pricing strategies
        PricingStrategy twPricingStrategy = new TwoWheelerPricingStrategy();
        PricingStrategy fwPricingStrategy = new FourWheelerPricingStrategy();

        // Create parking spots
        List<ParkingSpot> twoWheelerSpots = new ArrayList<>();
        twoWheelerSpots.add(new TwoWheelerSpot("T1", twPricingStrategy));
        twoWheelerSpots.add(new TwoWheelerSpot("T2", twPricingStrategy));

        List<ParkingSpot> fourWheelerSpots = new ArrayList<>();
        fourWheelerSpots.add(new FourWheelerSpot("F1", fwPricingStrategy));
        fourWheelerSpots.add(new FourWheelerSpot("F2", fwPricingStrategy));

        // Create managers
        TwoWheelerManager twoWheelerManager = new TwoWheelerManager(twoWheelerSpots);
        FourWheelerManager fourWheelerManager = new FourWheelerManager(fourWheelerSpots);
        TicketManager ticketManager = new TicketManager();

        // Create parking lot
        ParkingLot parkingLot = new ParkingLot(ticketManager, fourWheelerManager, twoWheelerManager);

        // Park a vehicle
        Vehicle vehicle = new TwoWheeler("MH-12-1234");
        parkingLot.park(vehicle);

        // Exit the vehicle
        parkingLot.exit(vehicle);
    }
}
