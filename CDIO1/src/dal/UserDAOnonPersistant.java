package dal;

import Data.ArraylistnonPersistens;
import dto.UserDTO;

import java.util.List;

public class UserDAOnonPersistant implements IUserDAO {

    ArraylistnonPersistens data1;

    public UserDAOnonPersistant() {
        data1 = new ArraylistnonPersistens();
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        UserDTO temp;
        for (int i = 0; i < data1.getUsers().size(); i++) {
            if (data1.getUsers().get(i).getUserId() == userId) {
                temp = data1.getUsers().get(i);
                return temp;
            }

        }
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return data1.getUsers();
    }

    @Override
    public void createUser(UserDTO user) throws DALException {

        data1.getUsers().add(user);

    }

    @Override
    public void updateUser(UserDTO user) throws DALException {


    }

    @Override
    public void deleteUser(int userId) throws DALException {
        for (int i = 0; i < data1.getUsers().size(); i++) {
            if (data1.getUsers().get(i).getUserId() == userId) {
                data1.getUsers().remove(i);
            }
        }
    }
}