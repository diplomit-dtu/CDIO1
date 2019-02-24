import dal.IUserDAO;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasicConnection {

    String databaseURL = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s132962";
    String userName = "s132962";
    String password = "aigJQGPdFIGulY8iALPsu";

    public void connect(String query) {
        ResultSet resultSet;
        try (Connection connection = DriverManager.getConnection(databaseURL, userName, password)) {

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException s) {
            System.out.println("No connection to mySQL");
        }
    }

    public static void main(String[] args){
        BasicConnection basic = new BasicConnection();
        basic.connect("SELECT * FROM user;");

    }

}
