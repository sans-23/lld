package lld.problems.parkingLot;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

// represent user interaction with entry and exit gates
public class TicketManager {
    private List<Ticket> tickets;

    public TicketManager(){
        tickets = new ArrayList<>();
    }
    
    public void issueTicket(ParkingSpot ps, Vehicle v){
        // vehicle arrive at gate (gate is closed)
        // issue ticket based on availability
        ps.assignVehicle(v.getLicenceString());
        tickets.add(new Ticket(v.getLicenceString(), LocalDateTime.now(), ps));
        System.out.println("Opens Gate");
        System.out.println("Vehicle "+ v.getLicenceString() +" assigned parking lot " + ps.getParkingSpotId());
        System.out.println("Gate closes");
    }

    public double calculateCost(Ticket ticket, PricingStrategy pricingStrategy){
        LocalDateTime entryTime = ticket.getTimestamp();
        LocalDateTime exitTime = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(entryTime, exitTime);
        if(hours == 0) hours = 1; // Minimum 1 hour charge
        return pricingStrategy.calcPrice((int)hours);
    }

    public Ticket getTicket(String vehicleNumber){
        for(Ticket t : tickets){
            if(t.getVehicleId().equals(vehicleNumber)){
                return t;
            }
        }
        return null;
    }

    public void removeTicket(Ticket ticket){
        tickets.remove(ticket);
    }
}
