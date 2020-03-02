package dal;

import Services.DatabaseIO;
import dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBUserDAO implements IUserDAO {
    DatabaseIO db = new DatabaseIO("root", "root", "localhost");

    @Override
    public UserDTO getUser(int userId) throws DALException {
        db.Connect();
        ResultSet rs = db.Query("SELECT " + userId + " FROM userdto");
        db.CloseConnection();
        UserDTO user = new UserDTO();
        try {
            user.setUserId(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setIni(rs.getString("ini"));
            user.addRole(rs.getString("roles"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
