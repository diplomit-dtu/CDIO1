package test;

import dal.IUserDAO;
import dal.UserDAOnonPersistant;
import dto.UserDTO;

public class DBnonPersistent {
    public static void main(String[] args) {
        IUserDAO iDAO = new UserDAOnonPersistant();
        UserDTO newUser = new UserDTO();
        //printUsers(iDAO);
        //TODO test new fields...
        newUser.setIni("test");
        newUser.addRole("Admin");
        newUser.setUserName("testName");
        newUser.setUserId(0);
        try {
            iDAO.createUser(newUser);
            System.out.println(iDAO.getUser(0));
        }catch (IUserDAO.DALException e){

        }
    }
}
