package dal;

import dto.UserDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDAOSQL implements IUserDAO{
    //Private final attributes
    private final String _DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String _END = "?characterEncoding=latin1&serverTimezone=UTC";

    //Changing attributes
    private String _url;
    private Connection _connection;

    //Optional
    private String _username = "Admin";
    private String _password = "password";

    /**
     * Uses a database
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
            throw new DALException("Class not found");
        }
        try {
            _connection = DriverManager.getConnection(_url,_username,_password);
        } catch (SQLException e) {
            throw new DALException("Cannot connect to database");
        }
    }
    private void closeConnection() throws DALException {
        try {
            _connection.close();
        } catch (SQLException e){
            throw new DALException("Cannot close connection to database");
        }
    }
    @Override
    public UserDTO getUser(int userId) throws DALException {
        openConnection();


        closeConnection();
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return null;
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