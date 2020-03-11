import dal.IUserDAO;
import dal.UserDAONonPersistent;
import dto.UserDTO;
import func.Func;
import func.IFunc;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        IUserDAO dao = new UserDAONonPersistent();
        IFunc func = new Func(dao);
        try {
            UserDTO user = func.createUser(11, "test","000000000","000000", Arrays.asList("Admin"));
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }

        CLI cli = new CLI(func, in);
        cli.run();
    }
}
