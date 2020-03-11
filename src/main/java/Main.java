import dal.IUserDAO;
import dal.UserDAONonPersistent;

import dto.UserDTO;
import func.Func;
import func.IFunc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IUserDAO.DALException {
        Scanner in = new Scanner(System.in);
        IUserDAO dao = new UserDAONonPersistent();
        for (UserDTO user :
                dao.getUserList()) {
            System.out.println(user);
        }
        IFunc func = new Func(dao);
        CLI cli = new CLI(func, in);
        cli.run();
    }
}
