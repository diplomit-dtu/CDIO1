package Services;

import java.sql.*;

public class DatabaseIO {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DatabaseURL;
    private String USER;
    private String PASS;
    private boolean connected = false;
    private Connection conn = null;
    private Statement stmt = null;


    public DatabaseIO(String USER, String PASSWORD, String URL) {
        this.USER = USER;
        this.PASS = PASSWORD;
        this.DatabaseURL = "jdbc:mysql://" + URL + ":3306/";
    }

    public void Connect() {
        if(!connected){
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Connecting to DB");
            try{
                conn = DriverManager.getConnection(DatabaseURL, USER, PASS);
                connected = true;
            }catch(SQLException e){
                System.out.println("Connecting failed");
                connected=false;
                e.printStackTrace();
            }
        } else{
            System.out.println("Already connected");
        }
    }
    public void Update(String query){
        if(!connected){
            System.out.println("Connect to a DB first");
        } else{
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(query);
                System.out.println(query+" has been executed");
            } catch (SQLException e) {
                System.out.println(query+" failed to execute");
                e.printStackTrace();
            }
        }
    }

    public ResultSet Query(String query){
        ResultSet result = null;
        if(!connected){
            System.out.println("Connect to a DB first");
        } else{
            try {
                stmt = conn.createStatement();
                result = stmt.executeQuery(query);
                System.out.println(query+" has been executed");
            } catch (SQLException e) {
                System.out.println(query+" failed to execute");
                e.printStackTrace();
            }
        }
        return result;
    }

    public void CloseConnection(){
        if(connected){
            try {
                conn.close();
                connected=false;
            } catch (SQLException e) {
                connected=true;
                e.printStackTrace();
            }

        }
    }

}
