package com.v2s2p.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;



/**
 * App driver.
 *
 * @author Myself
 */
public final class AppDriver {

    /** The logger for this class. */
    private static final Logger logger = LoggerFactory.getLogger(AppDriver.class);

    public static final String USERS_FILE_NAME = "Users.txt";

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
        
        Path path = Paths.get(USERS_FILE_NAME);
        User applicationUser = null;

        try {
            // Check if file exists
            if (Files.exists(path)) {
                logger.debug("File already exists.");
            } else {
                // Create the file if it does not exist
                Files.createFile(path);
                logger.debug("File created: " + USERS_FILE_NAME);
            }

            // Check if the file has more than 0 lines
            long lineCount = Files.lines(path).count();
            if (lineCount > 0) {
               
                logger.debug("The file has more than 0 lines.");
                //read users
                List<User> users = loadUsersFromFile(USERS_FILE_NAME);
                //print out to screen
               // Log each user with an index
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    logger.info("[" + (i ) + "] " + user.toString());
                }
                System.out.print("Enter number of user or negative number to create new user: ");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();  // Read user input as integer
               
                if (choice >= 0 && choice < users.size()) {
                    applicationUser = users.get(choice);
                } else if (choice < 0) {
                    applicationUser = getUserDetailsFromTerminal();
                    saveUserToFile(applicationUser, path);
                } else {
                    throw new Exception("Chosen user index is invalid");
                }

                scanner.close();

                //get user input if new or not
            } else {
                logger.debug("The file is empty or has 0 lines.");
                applicationUser = getUserDetailsFromTerminal();
                saveUserToFile(applicationUser, path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Chosen user: " + applicationUser.toString());

        
    }

    private static User getUserDetailsFromTerminal() {
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
        return user;
    }

    private static List<User> loadUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        Path path = Paths.get(filePath);

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(";;;");

                    if (parts.length == 3) {
                        String name = parts[0];
                        Integer level = Integer.parseInt(parts[1]);
                        Integer preferredRouteLength = Integer.parseInt(parts[2]);

                        User user = new User(name, level, preferredRouteLength);
                        users.add(user);
                    } else {
                        logger.warn("Skipping line due to incorrect format: " + line);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error reading users from file", e);
        } catch (NumberFormatException e) {
            logger.error("Error parsing number from file", e);
        }

        return users;
    }

    private static void saveUserToFile(User user, Path path) {
        // Append the new user to the file
        String userString = user.getName() + ";;;" + user.getLevel() + ";;;" + user.getPreferredRouteLength();
        try {
            Files.write(path, (userString + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            logger.info("User saved to file: " + user.toString());
        } catch (IOException e) {
            logger.error("Error saving user to file", e);
        }
    }
}
