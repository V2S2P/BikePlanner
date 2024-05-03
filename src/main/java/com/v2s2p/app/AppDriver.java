package com.v2s2p.app;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;
/**
 * App driver.
 *
 * @author Myself
 */
public final class AppDriver {

    /** The logger for this class. */
    private static final Logger logger = LoggerFactory.getLogger(AppDriver.class);

    /**
     * Private Constructor.
     */
    private AppDriver() {
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        logger.info("hello info");
        // Make new user use input from console to name user and set level anfd preferred length
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();  // Read user input as string
        logger.info("Hello, " + name + "!");

        System.out.print("Enter your needed route complexity level [1-20]: ");
        int level = scanner.nextInt();  // Read user input as integer
        logger.info("Chosen complexity level:" + String.valueOf(level));

        System.out.print("Enter maximum route length in km: ");
        int routeLength = scanner.nextInt();  // Read user input as integer
        logger.info("Chosen route length:" + String.valueOf(routeLength));

        scanner.close();  // Close the scanner
        User user = new User(name, level, routeLength);
        user.printUser();

        
    }
}
