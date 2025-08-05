import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseApp {

    // 1. JDBC connection settings
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASSWORD = "Naufal@100"; // ← change to your actual password

    static Connection conn;

    public static void main(String[] args) {
        try {
            // 2. Establish connection
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- Student Database Menu ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    addStudent(scanner);
                } else if (choice == 2) {
                    viewStudents();
                } else if (choice == 3) {
                    conn.close();
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Add a student to DB
    private static void addStudent(Scanner scanner) throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, course);
        stmt.executeUpdate();
        System.out.println("Student added.");
    }

    // 4. View all students
    private static void viewStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Student Records ---");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getString("name") +
                               " | " + rs.getInt("age") + " | " + rs.getString("course"));
        }
    }
}
