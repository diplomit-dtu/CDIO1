package dal;

import Data.MapnonPersistens;
import dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class UserDAOnonPersistant implements IUserDAO {

    MapnonPersistens data1;

    public UserDAOnonPersistant() {
        data1 = new MapnonPersistens();

    }

    @Override
    public UserDTO getUser(int userId) throws DALException {

        return data1.getUsers().get(userId);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
       return new ArrayList(data1.getUsers().values());

    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        if (data1.getUsers().containsKey(user.getUserId())){
            throw new DALException("\n" + "Bruger navn er optaget");
        }
        else
        data1.getUsers().put(user.getUserId(),user);

    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        if (data1.getUsers().containsKey(user.getUserId()))
        data1.getUsers().replace(user.getUserId(),user);
        else
            throw new DALException("\n" + "Brugeren eksistet ikke");

    }

    @Override
    public void deleteUser(int userId) throws DALException {
        if (data1.getUsers().containsKey(userId))
            data1.getUsers().remove(userId);
        else
            throw new DALException("\n" + "Brugeren eksisterer ikke");

    }
}