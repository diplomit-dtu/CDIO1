package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class UserDAOSQL implements IUserDAO{
    //Private final attributes
    private final String _DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String _END = "?characterEncoding=latin1&serverTimezone=UTC";

    //Changing attributes
    private String _url;
    private Connection _connection;
    private Statement _statement;

    //Optional
    private String _username = "SilasRindorf";
    private String _password = "trappeSumpTun";

    /**
     * Uses a database to
     * @param host Address of the host
     * @param port Port of the SQL database
     * @param database Name of database
     */
    public UserDAOSQL(String host, String port, String database) throws DALException {
        this._url = "jdbc:mysql://" + host + ":" + port + "/" + database + _END;
    }
    private void openConnection() throws DALException {
        try {
            Class.forName(_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DALException("openConnection Class not found");
        }

        try {
            _connection = DriverManager.getConnection(_url,_username,_password);
            _statement = _connection.createStatement();
        } catch (SQLException e) {
            throw new DALException("Cannot connect to database");
        }
    }
    private void closeConnection() throws DALException {
        try {
            _statement.close();
            _connection.close();
        } catch (SQLException e){
            throw new DALException("Cannot close connection to database");
        }
    }
    //rightPad method pads short columnnames and short columnvalues to same width
    private static String rightPad(String stringToPad, int width){
        StringBuilder stringBuilder = new StringBuilder(stringToPad);
        while(stringBuilder.length() <= width){
            stringBuilder.append(" ");
        }
        stringToPad = stringBuilder.toString();
        return stringToPad;
    }
    @Override
    public UserDTO getUser(int userId) throws DALException {
        openConnection();
        try {
            ResultSet resultSet = _statement.executeQuery("SELECT * FROM Users WHERE UserID=userId");
            ResultSetMetaData resultSetMetaData =resultSet.getMetaData();
        } catch (Exception e){
            throw new DALException("Cannot get user");
        }

        closeConnection();
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        openConnection();
        ArrayList<UserDTO> list = new ArrayList<>();
        try {
            ResultSet resultSet = _statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                list.add(new UserDTO());
                list.get(list.size() - 1).setUserId(resultSet.getInt("UserID"));
                list.get(list.size() - 1).setUserName(resultSet.getString("UserName"));
                list.get(list.size() - 1).setIni(resultSet.getString("Ini"));
                list.get(list.size() - 1).setUserCpr(resultSet.getString("cpr"));
                list.get(list.size() - 1).setPassword(resultSet.getString("Password"));
                list.get(list.size()-1).addRole(resultSet.getString("Roles"));
            }
        } catch (SQLException e){
            throw new DALException("Could not get user list");
        }
        closeConnection();
        return list;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {

    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}