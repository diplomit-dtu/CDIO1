package test;

import dal.IUserDAO;
import dal.UserDAO;
import dto.UserDTO;

public class fileTest {
    public static void main(String[] args) {
        //Laver en fil der hedder 5.txt med info herunder og læser den igen og printer initialerne
        UserDTO user = new UserDTO();
        user.setUserId(5);
        user.setUserName("Johnny");
        user.setIni("JOH");
        user.addRole("Operator");
        user.addRole("Admin");
        UserDAO user2 = new UserDAO();
        UserDTO userDTO = new UserDTO();
        try {
            user2.createUser(user);
            userDTO= user2.getUser(5);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        System.out.println(userDTO.getIni());
    }
}
