package com.example.studentdb;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentDatabaseApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
        System.out.println("=== Java + MySQL (JDBC) Student DBMS ===");
        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1: addUser(); break;
                    case 2: viewUsers(); break;
                    case 3: searchUsers(); break;
                    case 4: updateUser(); break;
                    case 5: deleteUser(); break;
                    case 6: System.out.println("Bye!"); return;
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                System.out.println("DB Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1) Add User");
        System.out.println("2) View All Users");
        System.out.println("3) Search Users by Name");
        System.out.println("4) Update User");
        System.out.println("5) Delete User");
        System.out.println("6) Exit");
    }

    private static void addUser() throws SQLException {
        System.out.println("-- Add User --");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        userDAO.addUser(new User(username, email));
        System.out.println("User added successfully.");
    }

    private static void viewUsers() throws SQLException {
        System.out.println("-- All Users --");
        List<User> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void searchUsers() throws SQLException {
        System.out.println("-- Search Users --");
        System.out.print("Query (name contains): ");
        String q = scanner.nextLine().trim();
        List<User> users = userDAO.searchByName(q);
        if (users.isEmpty()) {
            System.out.println("No matches.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void updateUser() throws SQLException {
        System.out.println("-- Update User --");
        int id = readInt("User ID to update: ");
        System.out.print("New username: ");
        String username = scanner.nextLine().trim();
        System.out.print("New email: ");
        String email = scanner.nextLine().trim();
        boolean ok = userDAO.updateUser(new User(id, username, email));
        System.out.println(ok ? "Updated." : "User not found.");
    }

    private static void deleteUser() throws SQLException {
        System.out.println("-- Delete User --");
        int id = readInt("User ID to delete: ");
        boolean ok = userDAO.deleteUser(id);
        System.out.println(ok ? "Deleted." : "User not found.");
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
