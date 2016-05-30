package cms;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) throws IOException {

        Scanner scanChoice = new Scanner(System.in);
        int choice;
        Log log = new Log();

        //Show main menu
        App.showMenu();

        while (true) {
            //Wait for user selecting menu.
            choice = scanChoice.nextInt();

            //Process user input.
            switch (choice) {
                case 1:
                    System.out.print("Please type the log file name : ");
                    log.readFile(new Scanner(System.in).nextLine());
                    break;

                case 2:
                    System.out.print("Please type the machine name: ");
                    log.listMachineReport(new Scanner(System.in).nextLine());
                    break;

                case 3:
                    System.out.print("Please type the machine name: ");
                    log.exportReportByMachine(new Scanner(System.in).nextLine());
                    break;

                case 4:
                    System.out.print("Please type the machine name: ");
                    log.listFailedEvent();
                    break;

                case 5:
                    log.exportFailedEventReport();
                    break;
                    
                case 6:
                    log.ShowAllLog();
                    break;

                case 7:
                    System.out.print("Goodbye");
                    System.exit(0);

                case 8:
                    App.showMenu();
                    break;
            }
        }

    }

    /**
     * Show main menu on screen.
     */
    public static void showMenu() {
        System.out.println();
        System.out.println("Please enter the following number 1-6");
        System.out.println("1: Import the log file (make sure the file located in the same folder with the program");
        System.out.println("2: List of machine that have reported the events");
        System.out.println("3: Export the list of Machine");
        System.out.println("4: Report of all events signifying 'failed' ");
        System.out.println("5: Export the reports of all events signifying 'failed' ");
        System.out.println("6: List the log contents");
        System.out.println("7: Exit from the system");
        System.out.println("8: Show menu");
        System.out.println();
    }

}
