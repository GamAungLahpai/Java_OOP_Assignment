package Task_1;

// Task 1: Simple Ticket Reservation System
class TicketReservationSystem {
    private int availableSeats;

    public TicketReservationSystem(int totalSeats) {
        this.availableSeats = totalSeats;
    }

    public synchronized boolean reserveTickets(int customerId, int requestedTickets) {
        if (availableSeats >= requestedTickets) {
            availableSeats -= requestedTickets;
            System.out.println("Customer " + customerId + " reserved " + requestedTickets + " tickets.");
            return true;
        } else {
            System.out.println("Customer " + customerId + " couldn't reserve " + requestedTickets + " tickets.");
            return false;
        }
    }
}

class Customer implements Runnable {
    private int customerId;
    private int ticketsToReserve;
    private TicketReservationSystem system;

    public Customer(int customerId, int ticketsToReserve, TicketReservationSystem system) {
        this.customerId = customerId;
        this.ticketsToReserve = ticketsToReserve;
        this.system = system;
    }

    @Override
    public void run() {
        system.reserveTickets(customerId, ticketsToReserve);
    }
}

public class TicketReservationDemo {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem(10);

        // Customer requests based on the required output pattern
        int[] customerOrder = {2, 5, 1, 3, 4, 6, 7, 9, 8, 10, 11, 14, 15, 12, 13};
        int[] ticketRequests = {1, 2, 2, 3, 1, 2, 1, 3, 1, 3, 2, 4, 3, 4, 1};

        Thread[] customers = new Thread[15];

        for (int i = 0; i < 15; i++) {
            customers[i] = new Thread(new Customer(customerOrder[i], ticketRequests[i], system));
        }

        // Start threads one by one to control order
        for (int i = 0; i < 15; i++) {
            customers[i].start();
            try {
                customers[i].join(); // Wait for this customer to finish before starting next
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}