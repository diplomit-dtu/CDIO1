package func;

import dal.IUserDAO;
import dto.UserDTO;

import java.util.List;

public class Func implements IFunc {
    IUserDAO dao;

    public Func(IUserDAO dao){this.dao = dao;}

    @Override
    public UserDTO createUser(int userID, String userName, String cpr, String password, List<String> roles) {
        UserDTO user = new UserDTO();
        user.setUserId(userID);
        user.setUserName(userName);
        user.setUserCpr(cpr);
        user.setPassword(password);
        user.setRoles(roles);

        try {
            dao.createUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<UserDTO> getUserList() throws IUserDAO.DALException {
        return dao.getUserList();
    }

    @Override
    public UserDTO getUser(int userID) throws IUserDAO.DALException {
        return dao.getUser(userID);
    }

    @Override
    public UserDTO deleteUser(int userID) throws IUserDAO.DALException {
        UserDTO user = getUser(userID);
        dao.deleteUser(userID);
        return user;
    }
}
