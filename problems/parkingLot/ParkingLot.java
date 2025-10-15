package lld.problems.parkingLot;

// Facade strategy

public class ParkingLot {
    TicketManager ticketManager;
    FourWheelerManager fourWheelerManager;
    TwoWheelerManager twoWheelerManager;

    public ParkingLot(TicketManager ticketManager, FourWheelerManager fourWheelerManager, TwoWheelerManager twoWheelerManager){
        this.ticketManager = ticketManager;
        this.fourWheelerManager = fourWheelerManager;
        this.twoWheelerManager = twoWheelerManager;
    }


    public void park(Vehicle v){
        ParkingSpot availableSpot = null;
        if(v.getVehicleType().equals(VehicleType.TWO_WHEELER)){
            availableSpot = twoWheelerManager.findParkingSpot();
        } else if(v.getVehicleType().equals(VehicleType.FOUR_WHEELER)){
            availableSpot = fourWheelerManager.findParkingSpot();
        }


        if (availableSpot != null) {
            availableSpot.assignVehicle(v.getLicenceString());
            ticketManager.issueTicket(availableSpot, v);
        } else {
            System.out.println("No available parking spot for " + v.getVehicleType());
        }
    }

    public void exit(Vehicle v){
        Ticket ticket = ticketManager.getTicket(v.getLicenceString());
        if(ticket != null){
            ParkingSpot spot = ticket.getParkingSpot();
            double cost = ticketManager.calculateCost(ticket, spot.getPricingStrategy());
            System.out.println("Parking cost for " + v.getLicenceString() + " is " + cost);
            if(v.getVehicleType().equals(VehicleType.TWO_WHEELER)){
                twoWheelerManager.freeParkingSpot(spot);
            } else if(v.getVehicleType().equals(VehicleType.FOUR_WHEELER)){
                fourWheelerManager.freeParkingSpot(spot);
            }
            ticketManager.removeTicket(ticket);
        } else {
            System.out.println("No ticket found for vehicle " + v.getLicenceString());
        }
    }
}
