package com.pluralsight;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "yearup";

        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection connection = DriverManager.getConnection(url, username, password);

        while (true) {
            System.out.print("What do you want to do? \n" +
                    "1) Display all products \n2) Display all customers \n3) Display all categories \n0) Exit \n" +
                    "Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("invalid option");
                    break;
            }

            String n = "30";

            //String query = ;
            String query2 = "SELECT * FROM Customers";
            String query3 = "SELECT * FROM Categories";

            Connection connection = null;

            PreparedStatement statement = null;
            PreparedStatement statement2 = null;
            PreparedStatement statement3 = null;

            ResultSet results = null;
            ResultSet results2 = null;
            ResultSet results3 = null;
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Products") {

                statement = connection.prepareStatement(query);
                statement2 = connection.prepareStatement(query2);
                statement3 = connection.prepareStatement(query3);

                // Executing query
                results = statement.executeQuery();
                results2 = statement2.executeQuery();
                results3 = statement3.executeQuery();

                //Products!!
                System.out.println("Id   Name                             Price  Stock      \n---- -------------------------------- ------ ------ \n");
                while (results.next()) {
                    // Replace with your column names and types
                    String id = formatSpaces(results.getString(1), 4);
                    String productName = results.getString("ProductName");
                    String price = results.getString("UnitPrice");
                    String inStock = results.getString("UnitsInStock");

                    System.out.printf("%s %s %s %s \n", id, productName, price, inStock);
                }

                //Company!!
                System.out.println("Id   Name                             Price  Stock      \n---- -------------------------------- ------ ------ \n");
                while (results2.next()) {
                    String name = results2.getString("ContactName");
                    String company = results2.getString("CompanyName");
                    String city = results2.getString("City");
                    String country = results2.getString("Country");
                    String phone = results2.getString("Phone");

                    System.out.printf("%s %s %s %s %s \n", name, company, city, country, phone);
                }

                System.out.println("Id   Name                             Price  Stock      \n---- -------------------------------- ------ ------ \n");
                while (results3.next()) {
                    String id = formatSpaces(results.getString(1), 4);
                    String category = results3.getString("CategoryName");

                    System.out.printf("%s %s \n", id, category);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
// close the resources
                if (results != null) results.close();
                if (results2 != null) results2.close();
                if (results3 != null) results3.close();
                if (statement != null) statement.close();
                if (statement2 != null) statement2.close();
                if (statement3 != null) statement3.close();
                if (connection != null) connection.close();
            }
        }
    }

    /*
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT ProductName, UnitPrice FROM Products WHERE UnitPrice > ? ");
        //The question mark is a placeholder for later

        preparedStatement.setString(1, n);
        //protect from SQL injection
        //the parameterindex is for the question mark on line 17

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String productName = resultSet.getString("ProductName");
            String UnitPrice = resultSet.getString("UnitPrice");
            System.out.printf("ProductName = %s, UnitPrice = %s \n", productName, UnitPrice);
            //change the index to product name
            //%s for each value
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
 */
    public static String formatSpaces(String v, int maxLength) {
        String newV = v;
        for (int i = 0; i < maxLength - v.length(); i++) {
            newV += " ";
        }
        return newV;
    }
}
