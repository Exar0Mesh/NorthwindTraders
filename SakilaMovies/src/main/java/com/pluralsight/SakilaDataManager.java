package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public class SakilaDataManager {
    private BasicDataSource dataSource;

    public SakilaDataManager(String username, String password) {
        this.dataSource = new BasicDataSource();
        this.dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        this.dataSource.setUsername(username);
        this.dataSource.setPassword(password);
    }

    public List<Actor> getActorByName(String name) {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM actor WHERE first_name LIKE or last_name LIKE ?";
        try(
                Connection connection = this.dataSource.getConnection(); // Here is the database connection right here!
                PreparedStatement statement = connection.prepareStatement(query);
                ) {

            statement.setString(1,"%" + name + "%");
            statement.setString(2,"%" + name + "%");
            // This allows you to search both first_name and last_name for results using just "name". Smart!

            try(ResultSet results = statement.executeQuery()) {

                while(results.next()) {
                    String actorId = results.getString("actor_id");
                    String firstName = results.getString("first_name");
                    String lastName = results.getString("last_name");
                    actors.add(new Actor(actorId, firstName, lastName));
                }

            } catch (SQLException e) {
                    e.printStackTrace();
                }
            // Get into the habit of doing this one separately.

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<Film> getFilmByActor(String something) {
        List<Film> films = new ArrayList<>();
        String query = "SELECT\n" + "\t*\n" + "FROM\n" + "\tfilm f\n" +
                "\ttJOIN film_actor fa ON (f.film_id = fa.film_id)\n" + "WHERE"

        try(
                Connection connection = this.dataSource.getConnection(); // Here is the database connection right here!
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setString(1,"%" + films + "%");
    }

    /*
    public void actorQuery(String query, Object[] params) {
        scanner = new Scanner(System.in);
        System.out.println("What is your actors name? ");
        System.out.print("Enter their first name: ");
        String actorFirst = scanner.nextLine();
        System.out.print("\nEnter their last name: ");
        String actorLast = scanner.nextLine();

        // Method to execute a query using PreparedStatement

        try {
            // Create the prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameters for the prepared statement
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            // Execute the update or query (INSERT, UPDATE, DELETE, or SELECT)
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // If SELECT statement, handle the ResultSet
            if (query.trim().toUpperCase().startsWith("SELECT")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    // Process the result set
                    System.out.println("Data: " + resultSet.getString(1)); // Example, customize for your needs
                }
            }

            // Close the prepared statement after execution
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     */
}