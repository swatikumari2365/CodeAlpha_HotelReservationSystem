import java.util.*;

class Room {
    String category;
    int number;
    double price;
    boolean isAvailable;

    Room(String category, int number, double price) {
        this.category = category;
        this.number = number;
        this.price = price;
        this.isAvailable = true;
    }
}

class Booking {
    String customerName;
    Room room;
    int nights;
    double totalAmount;

    Booking(String customerName, Room room, int nights) {
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.totalAmount = room.price * nights;
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room("Single", 101, 100));
        rooms.add(new Room("Double", 102, 150));
        rooms.add(new Room("Suite", 201, 250));
        rooms.add(new Room("Single", 103, 100));
        rooms.add(new Room("Double", 104, 150));
        rooms.add(new Room("Suite", 202, 250));
    }

    private static void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room Number: " + room.number + ", Category: " + room.category + ", Price: $" + room.price);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.next();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        Room room = findRoom(roomNumber);
        if (room != null && room.isAvailable) {
            Booking booking = new Booking(customerName, room, nights);
            bookings.add(booking);
            room.isAvailable = false;
            processPayment(booking);
            System.out.println("Reservation made successfully.");
        } else {
            System.out.println("Room not available.");
        }
    }

    private static Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.number == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private static void processPayment(Booking booking) {
        System.out.println("Processing payment of $" + booking.totalAmount + " for " + booking.customerName);
        // In a real system, payment processing logic would be implemented here
        System.out.println("Payment processed successfully.");
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.next();

        boolean bookingFound = false;
        for (Booking booking : bookings) {
            if (booking.customerName.equals(customerName)) {
                bookingFound = true;
                System.out.println("Booking Details:");
                System.out.println("Customer Name: " + booking.customerName);
                System.out.println("Room Number: " + booking.room.number);
                System.out.println("Room Category: " + booking.room.category);
                System.out.println("Number of Nights: " + booking.nights);
                System.out.println("Total Amount: $" + booking.totalAmount);
            }
        }
        if (!bookingFound) {
            System.out.println("No booking found for " + customerName);
        }
    }
}