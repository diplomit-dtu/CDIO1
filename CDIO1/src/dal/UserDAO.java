package dal;

import Services.TxtReader;
import dto.UserDTO;

import java.util.List;


public class UserDAO implements IUserDAO {
    TxtReader txtReader;
    @Override
    public UserDTO getUser(int userId) throws DALException{
       txtReader.openFile("src/Services/users/",userId +".txt");
       txtReader.readLines();
       return null;
    }
    @Override
    public List<UserDTO> getUserList() throws DALException{
        return null;
    }
    @Override
    public void createUser(UserDTO user) throws DALException{

    }
    @Override
    public void updateUser(UserDTO user) throws DALException{

    }
    @Override
    public void deleteUser(int userId) throws DALException{

    }
}
