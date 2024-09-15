package cinema;

import java.util.Scanner;

public class Cinema {

    static int rowTotal;
    static int seatTotal;
    static int priceFontTicket = 0;
    static int priceBackTicket = 0;
    static int frontSeats = rowTotal / 2;
    static int backSeats = rowTotal - frontSeats;
    static Character[][] room;
    static Scanner scanner;
    static int currentIncome;
    static int totalIncome;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows:\n> ");
        rowTotal = scanner.nextInt();

        System.out.print("Enter the number of seats in each row:\n> ");
        seatTotal = scanner.nextInt();
        System.out.println();

        room = new Character[rowTotal + 1][seatTotal + 1];

        initRoom();


        if (rowTotal * seatTotal <= 60) {
            priceFontTicket = 10;
            priceBackTicket = 10;
        } else {
            priceFontTicket = 10;
            priceBackTicket = 8;
        }

        //int total = (seatTotal * frontSeats * priceFontTicket)
        //        + (seatTotal * backSeats * priceBackTicket);

        //System.out.print("Total income:\n$" + total);

        while (true) {
            showMenu();
            switch (scanner.nextInt()) {
                case 1 -> {
                    showRoom();
                }
                case 2 -> {
                    buyTicket();
                }
                case 3 -> {
                    showStat();
                }
                case 0 -> {
                    System.out.println();
                    return;
                }
            }
        }
    }

    public static void showStat() {
        float proc = (float) countBuyTickets() / (rowTotal * seatTotal) * 100;

        System.out.println("\nNumber of purchased tickets: " + countBuyTickets());
        System.out.printf("Percentage: %.2f%%%n", proc);
        System.out.println("Current income: $" + currentIncome);

        int total = (seatTotal * frontSeats * priceFontTicket)
                + (seatTotal * backSeats * priceBackTicket);

        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static int countBuyTickets() {
        int total = 0;
        currentIncome = 0;
        totalIncome = 0;

        for (int i = 1; i < room.length; i++) {
            for (int j = 1; j < room[0].length; j++) {
                if (room[i][j].equals('B')) {
                    total++;
                    currentIncome += costTicket(i, j);
                }
                totalIncome += costTicket(i, j);
            }
        }
        return total;
    }

    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    public static void buyTicket() {
        System.out.print("\nEnter a row number:\n> ");
        int row = scanner.nextInt();

        System.out.print("Enter a seat number in that row:\n> ");
        int seat = scanner.nextInt();

        if (row > rowTotal || seat > seatTotal) {
            System.out.println("\nWrong input!\n");
            buyTicket();
        } else if (room[row][seat].equals('B')) {
            System.out.println("\nThat ticket has already been purchased!\n");
            buyTicket();
        } else {
            System.out.println("Ticket price: $" + costTicket(row, seat));
            System.out.println();
            markSeat(row, seat);
        }
    }

    public static void markSeat(int row, int seat) {
        if ((row >= 1 && row <= rowTotal) && (seat >= 1 && seat <= seatTotal))
            room[row][seat] = 'B';
    }

    public static int costTicket(int row, int seat) {
        if ((rowTotal * seatTotal > 60) && (row > rowTotal / 2)) {
            return 8;
        } else {
            return 10;
        }
    }

    public static void showRoom() {
        System.out.println("\nCinema:");
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                System.out.print(room[i][j] + " ");
                if (j == room[0].length - 1) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println();
    }

    public static void initRoom() {
        for (int i = 0; i < room.length; i++) {
            if (i == 0) {
                room[i][0] = ' ';
                for (int j = 1; j < room[0].length; j++) {
                    room[i][j] = (char) (j + '0');
                }
                continue;
            }
            for (int j = 0; j < room[0].length; j++) {
                if (j == 0) {
                    room[i][j] = (char) (i + '0');
                } else {
                    room[i][j] = 'S';
                }
            }
        }
    }
}
