package ac.uca.miniprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DataBase {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public static void initializeDataBase() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {

            // Create Patients table
            String sql1 = "CREATE TABLE IF NOT EXISTS Patients (\n" +
                    "    patient_id INTEGER PRIMARY KEY,\n" +
                    "    first_name VARCHAR(50),\n" +
                    "    last_name VARCHAR(50),\n" +
                    "    date_of_birth DATE,\n" +
                    "    phone_number VARCHAR(20),\n" +
                    "    email VARCHAR(100),\n" +
                    "    address TEXT,\n" +
                    "    medical_history TEXT\n" +
                    ");";
            statement.execute(sql1);

            // Create Appointments table
            String sql2 = "CREATE TABLE IF NOT EXISTS Appointments (\n" +
                    "    appointment_id INTEGER PRIMARY KEY,\n" +
                    "    patient_id INTEGER,\n" +
                    "    appointment_date DATE,\n" +
                    "    appointment_time TIME,\n" +
                    "    reason TEXT,\n" +
                    "    status VARCHAR(20),\n" +
                    "    reminder_sent INTEGER DEFAULT 0,\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)\n" +
                    ");";
            statement.execute(sql2);

            // Create Receipts table
            String sql3 = "CREATE TABLE IF NOT EXISTS Receipts (\n" +
                    "    receipt_id INTEGER PRIMARY KEY,\n" +
                    "    patient_id INTEGER,\n" +
                    "    appointment_id INTEGER,\n" +
                    "    total_amount DECIMAL(10, 2),\n" +
                    "    payment_status VARCHAR(20),\n" +
                    "    payment_date DATE,\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),\n" +
                    "    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id)\n" +
                    ");";
            statement.execute(sql3);

            // Create Reminder_Log table
            String sql4 = "CREATE TABLE IF NOT EXISTS Reminder_Log (\n" +
                    "    reminder_id INTEGER PRIMARY KEY,\n" +
                    "    appointment_id INTEGER,\n" +
                    "    patient_id INTEGER,\n" +
                    "    email_sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                    "    reminder_type VARCHAR(50),\n" +
                    "    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id),\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)\n" +
                    ");";
            statement.execute(sql4);

            // Create Admins table
            String sql5 = "CREATE TABLE IF NOT EXISTS Admins (\n" +
                    "    admin_id INTEGER PRIMARY KEY,\n" +
                    "    username VARCHAR(50) UNIQUE NOT NULL,\n" +
                    "    password VARCHAR(255) NOT NULL,\n" +
                    "    email VARCHAR(100) UNIQUE,\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                    ");";
            statement.execute(sql5);

            System.out.println("Tables created successfully");

            // Check if there's an admin in the Admins table
            String sql6 = "SELECT COUNT(*) AS count FROM Admins";
            ResultSet resultSet = statement.executeQuery(sql6);

            if (resultSet.next() && resultSet.getInt("count") == 0) {
                // No admin exists, insert default admin
                String defaultAdmin = "INSERT INTO Admins (username, password, email) " +
                        "VALUES ('admin', 'admin@123', 'admin@uca.ac.ma');";
                statement.execute(defaultAdmin);
                System.out.println("Default admin created.");
            } else {
                System.out.println("Admin already exists.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
