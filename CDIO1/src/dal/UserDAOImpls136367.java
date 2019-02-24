package dal;

import dto.UserDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


public class UserDAOImpls136367 implements IUserDAO {
    //TODO Make a connection to the database
    private Connection createConnection() throws DALException {
        try {
            return DriverManager.getConnection("ec2-52-30-211-3.eu-west-1.compute.amazonaws.com"
                    + "user=s176367&password=FzhBmzA9QCCDAU3KnIa55");
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }
    private int	userId;
    private String userName;
    private String ini;
    private List<String> roles;
    Statement stmt = null;
    Connection conn = null;

    @Override
    public UserDTO getUser(int userId) throws DALException {
        //TODO Implement this

        Connection c = createConnection();


        // create a new scannerUserID with the specified String Object
        Scanner scannerUserID = new Scanner(System.in);

        // find the next int token and print it
        // loop for the whole scannerUserID
        while (scannerUserID.hasNext()) {

            // if the next is a int, print found and the int
            if (scannerUserID.hasNextInt()) {
                System.out.println("Found User :" + scannerUserID.nextInt());
                userId = scannerUserID.nextInt();
            }
            // if no int is found, print "Not Found:" and the token
            System.out.println("Not Found, try again :" + scannerUserID.next());

        }

        // close the scannerUserID
        scannerUserID.close();



        //TODO: Make a user from the resultset
        UserDTO user = new UserDTO();

        try {
            c.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
        return user;
    }



    @Override
    public List<UserDTO> getUserList() throws DALException {
        //TODO Implement this
        return null;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        //TODO Implement this
        createConnection();
        stmt = conn.createStatement();
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        //TODO Implement this
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        //TODO Implement this
    }
}
