package test;

import dal.IUserDAO;
import dal.UserDAO;
import dto.UserDTO;

public class fileTest {
    public static void main(String[] args) {
        UserDTO user = new UserDTO();
        user.setUserId(5);
        user.setUserName("Johnny");
        user.setIni("JOH");
        user.addRole("Operator");
        UserDAO user2 = new UserDAO();
        try {
            user2.createUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }
}
