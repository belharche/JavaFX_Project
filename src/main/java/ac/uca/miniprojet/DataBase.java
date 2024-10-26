package ac.uca.miniprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    public static void initializeDataBase(){
        try(Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement= connection.createStatement()
        ) {
            String sql1="CREATE TABLE Patients (\n" +
                    "    patient_id INTEGER PRIMARY KEY,\n" +
                    "    first_name VARCHAR(50),\n" +
                    "    last_name VARCHAR(50),\n" +
                    "    date_of_birth DATE,\n" +
                    "    phone_number VARCHAR(20),\n" +
                    "    email VARCHAR(100),\n" +
                    "    address TEXT,\n" +
                    "    medical_history TEXT\n" +
                    ");";
            String sql2="CREATE TABLE Appointments (\n" +
                    "    appointment_id INTEGER PRIMARY KEY,\n" +
                    "    patient_id INTEGER,\n" +
                    "    appointment_date DATE,\n" +
                    "    appointment_time TIME,\n" +
                    "    reason TEXT,\n" +
                    "    status VARCHAR(20),  -- 'Scheduled', 'Completed', or 'Cancelled'\n" +
                    "    reminder_sent INTEGER DEFAULT 0,  -- 0 for FALSE, 1 for TRUE\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)\n" +
                    ");";
            String sql3="CREATE TABLE Receipts (\n" +
                    "    receipt_id INTEGER PRIMARY KEY,\n" +
                    "    patient_id INTEGER,\n" +
                    "    appointment_id INTEGER,\n" +
                    "    total_amount DECIMAL(10, 2),\n" +
                    "    payment_status VARCHAR(20),  -- 'Paid' or 'Pending'\n" +
                    "    payment_date DATE,\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),\n" +
                    "    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id)\n" +
                    ");";
            String sql4="CREATE TABLE Reminder_Log (\n" +
                    "    reminder_id INTEGER PRIMARY KEY,\n" +
                    "    appointment_id INTEGER,\n" +
                    "    patient_id INTEGER,\n" +
                    "    email_sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                    "    reminder_type VARCHAR(50),  -- e.g., 'Control Session Reminder'\n" +
                    "    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id),\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)\n" +
                    ");";
            statement.execute(sql1);
            statement.execute(sql2);
            statement.execute(sql3);
            statement.execute(sql4);
            System.out.println("Table crées" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }{


    }

}

    public static void main(String[] args) {
        initializeDataBase();
    }
}
