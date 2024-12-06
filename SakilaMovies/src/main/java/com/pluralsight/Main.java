package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.hca.jdbc.UsingDriverManager <username> " +
                            "<password>");
            System.exit(1);
        }
// Get the username and password
        String username = args[0];
        String password = args[1];

        SakilaDataManager dataManager = new SakilaDataManager(username, password);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter actors first name or last name to search: ");
        String name = scanner.nextLine();

        List<Actor> matchedActors = dataManager.getActorByName(name.toUpperCase().trim());

        matchedActors.stream().forEach(actor -> {System.out.println(actor);});




    }
    /*
     try {
        SakilaDataManager dataManager = new SakilaDataManager("jdbc:mysql://localhost:3306/sakila", "root", "yearup");
        ResultSet rs = dataManager.fetchData("SELECT * FROM Actor");
        while (rs.next()) {
            System.out.println("Actor ID: " + rs.getInt("actor_id") +
                    ", First Name: " + rs.getString("first_name") +
                    ", Last Name: " + rs.getString("last_name"));
        }

        ResultSet rs2 = dataManager.fetchData("SELECT * FROM Film");
        while (rs2.next()) {
            System.out.println("Film ID: " + rs2.getInt("film_id") +
                    ", title: " + rs2.getString("title") +
                    ", description: " + rs2.getString("description") +
                    ", release year: " + rs2.getString("release_year") +
                    ", length: " + rs2.getString("length"));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

     */

            /*
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        SakilaDataManager dataManager = new SakilaDataManager(dataSource);

        doSimpleQuery(dataSource);

        doAnotherQuery(dataSource);
    }
        private static void doSimpleQuery (DataSource dataSource) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Last name of Actor you like? ");
            String actor = scanner.nextLine();
            //Displays all actors with that last name
            //What if someone typed the last name of an actor not in the database?

// Create the connection and prepared statement
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT first_name, last_name FROM actor " +
                                         "WHERE last_name LIKE ? ORDER BY first_name");
            ) {
// Set any required parameters
                preparedStatement.setString(1, "%"+ actor + "%");
// Execute the query
                try (ResultSet resultSet =
                             preparedStatement.executeQuery()
                ) {
// Process the results
                    // use the first call to next() to see if there are records
                    if (resultSet.next()) {
                        System.out.println("Your matches are: \n");
                        do {
                            System.out.printf( "first_name = %s, last_name = %s;\n",
                                        resultSet.getString(1), resultSet.getString(2));
                        } while (resultSet.next());
                    }
                    else {
                        System.out.println("No matches!");
                    }

                }
            }
            catch (SQLException e) {
// This will catch all SQLExceptions occurring
// in the try block, including those in nested
// try statements
                e.printStackTrace();
            }
    }
    private static void doAnotherQuery (DataSource dataSource) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is an actor you want to see movies of? ");
        System.out.print("Enter their first name: ");
        String actorFirst = scanner.nextLine();
        System.out.print("Enter their last name: ");
        String actorLast = scanner.nextLine();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT a.first_name, a.last_name, f.title FROM actor as a " +
                                     "JOIN film_actor fa ON (a.actor_id = fa.actor_id)" +
                                     "JOIN film f ON (fa.film_id = f.film_id)"
                                     + "WHERE first_name LIKE ? AND last_name LIKE ?");
        ) {
// Set any required parameters
            preparedStatement.setString(1, "%"+ actorFirst + "%");
            preparedStatement.setString(2, "%"+ actorLast + "%");

            //System.out.println(preparedStatement);

            try (ResultSet resultSet =
                         preparedStatement.executeQuery()
            ) {
// Process the results
                // use the first call to next() to see if there are records
                if (resultSet.next()) {
                    System.out.println("Your matches are: \n");
                    do {
                        System.out.printf( "first_name = %s, last_name = %s, title = %s, ;\n",
                                resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                    } while (resultSet.next());
                }
                else {
                    System.out.println("No matches!");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

             */
}