package mavenEmployee;



import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employe_menu {
    Connection connection;

    public void ConnectDB() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "ASSESMENT";
        String password = "19062001";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int UpdateDB(String sql, Object[] params) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String arg[]) {
        Employe_menu emp = new Employe_menu();
        Scanner scan = new Scanner(System.in);
        emp.ConnectDB();

        System.out.print("enter the employee ID : ");
        int employee_id = scan.nextInt();
        System.out.print("Enter the billing rate : ");
        int billing_rate = scan.nextInt();

        String sql = "INSERT INTO Employee2 (employee_id, billing_rate) VALUES (?, ?)";
        Object[] params = {employee_id, billing_rate};
        int rowsInserted = emp.UpdateDB(sql, params);
        if (rowsInserted > 0)
            System.out.println("Employee was inserted successfully!");
    }
}