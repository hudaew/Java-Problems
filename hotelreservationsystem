import java.io.*;
import java.util.Scanner;

class HotelRoom {

    int roomnumber;
    String category;
    double price;
    boolean available;

    HotelRoom(int roomnumber,
              String category,
              double price) {

        this.roomnumber = roomnumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }
}

public class HotelReservationSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        HotelRoom[] rooms = {

            new HotelRoom(101,"Standard",5000),
            new HotelRoom(102,"Standard",5000),

            new HotelRoom(201,"Deluxe",8000),
            new HotelRoom(202,"Deluxe",8000),

            new HotelRoom(301,"Suite",12000),
            new HotelRoom(302,"Suite",12000)
        };

        int choice;

        do {

            System.out.println("\n===== HOTEL SYSTEM =====");

            System.out.println("1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");

            System.out.print("Choice: ");
            choice = input.nextInt();

            switch(choice) {

                case 1:

                    System.out.println("\nAvailable Rooms");

                    for(int i=0;i<rooms.length;i++) {

                        if(rooms[i].available) {

                            System.out.println(
                                rooms[i].roomnumber +
                                " | " +
                                rooms[i].category +
                                " | Rs." +
                                rooms[i].price
                            );
                        }
                    }

                    break;

                case 2:

                    System.out.print("Enter room number: ");
                    int room = input.nextInt();

                    boolean found = false;

                    for(int i=0;i<rooms.length;i++) {

                        if(rooms[i].roomnumber == room &&
                           rooms[i].available) {

                            found = true;

                            input.nextLine();

                            System.out.print("Customer Name: ");
                            String name =
                                input.nextLine();

                            System.out.println(
                                "Amount to Pay: Rs."
                                + rooms[i].price);

                            System.out.print(
                                "Enter payment amount: ");

                            double payment =
                                input.nextDouble();

                            if(payment >=
                               rooms[i].price) {

                                rooms[i].available =
                                    false;

                                try {

                                    FileWriter fw =
                                      new FileWriter(
                                      "bookings.txt",
                                      true);

                                    fw.write(
                                      name + "," +
                                      rooms[i].roomnumber +
                                      "," +
                                      rooms[i].category +
                                      "," +
                                      rooms[i].price +
                                      "\n");

                                    fw.close();

                                }
                                catch(Exception e) {

                                    System.out.println(
                                    "File Error");
                                }

                                System.out.println(
                                "Booking Successful");
                            }

                            else {

                                System.out.println(
                                "Payment Failed");
                            }
                        }
                    }

                    if(!found) {

                        System.out.println(
                        "Room Not Available");
                    }

                    break;

                case 3:

                    System.out.print(
                    "Enter room number to cancel: ");

                    int cancel =
                        input.nextInt();

                    for(int i=0;i<rooms.length;i++) {

                        if(rooms[i].roomnumber
                           == cancel) {

                            rooms[i].available =
                                true;

                            System.out.println(
                            "Booking Cancelled");
                        }
                    }

                    break;

                case 4:

                    try {

                        BufferedReader br =
                          new BufferedReader(
                          new FileReader(
                          "bookings.txt"));

                        String line;

                        System.out.println(
                        "\nBooking Details");

                        while((line =
                               br.readLine())
                               != null) {

                            System.out.println(
                            line);
                        }

                        br.close();
                    }

                    catch(Exception e) {

                        System.out.println(
                        "No Booking Found");
                    }

                    break;

                case 5:

                    System.out.println(
                    "Thank You");

                    break;

                default:

                    System.out.println(
                    "Invalid Choice");
            }

        } while(choice != 5);

        input.close();
    }
}
