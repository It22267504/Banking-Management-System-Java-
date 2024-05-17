package mngLoanApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/loan?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Tmka@2002";

    private static final String INSERT_APPLICATION_SQL = "INSERT INTO request (name, accountNumber, phoneNumber, email, photo, nic, type, amount, years) VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?);";
    private static final String SELECT_APPLICATION_BY_ID = "SELECT id, name, accountNumber, phoneNumber, email, photo, nic , type, amount, years FROM request WHERE id = ?";
    private static final String SELECT_ALL_APPLICATIONS = "SELECT * FROM request";
    private static final String DELETE_APPLICATION_SQL = "DELETE FROM request WHERE id = ?";
    private static final String UPDATE_APPLICATION_SQL = "UPDATE request SET name = ?, accountNumber = ?, phoneNumber = ?, email = ?, photo = ?, nic = ? , type= ? ,amount = ? , years= ? WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    // Create or insert application
    public void insertApplication(Application application) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPLICATION_SQL);
            
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getAccountNumber());
            preparedStatement.setString(3, application.getPhoneNumber());
            preparedStatement.setString(4, application.getEmail());
            preparedStatement.setString(5, application.getPhoto());
            preparedStatement.setString(6, application.getNic());
            preparedStatement.setString(7, application.getType());
            preparedStatement.setString(8, application.getAmount());
            preparedStatement.setString(9, application.getYears());
            
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update application
    public void updateApplication(Application application) {
        try {
            Connection connection = getConnection();
            
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPLICATION_SQL);
           
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getAccountNumber());
            preparedStatement.setString(3, application.getPhoneNumber());
            preparedStatement.setString(4, application.getEmail());
            preparedStatement.setString(5, application.getPhoto());
            preparedStatement.setString(6, application.getNic());
            preparedStatement.setString(7, application.getType());
            preparedStatement.setString(8, application.getAmount());
            preparedStatement.setString(9, application.getYears());
            
            preparedStatement.setInt(10, application.getId());
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Select application by id
    public Application selectApplication(int id) {
        Application application = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPLICATION_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String accountNumber = rs.getString("accountNumber");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String photo = rs.getString("photo");
                String nic = rs.getString("nic");
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                String years = rs.getString("years");
                application = new Application(id, name, accountNumber, phoneNumber, email, photo, nic, type, amount, years);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }

    // Select all applications
    public List<Application> selectAllApplications() {
        List<Application> applications = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APPLICATIONS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String accountNumber = rs.getString("accountNumber");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String photo = rs.getString("photo");
                String nic = rs.getString("nic");
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                String years = rs.getString("years");
                
                applications.add(new Application(id, name, accountNumber, phoneNumber, email, photo, nic, type, amount, years));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    // Delete application
    public boolean deleteApplication(int id) {
        boolean rowDeleted;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_APPLICATION_SQL);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            rowDeleted = false;
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
