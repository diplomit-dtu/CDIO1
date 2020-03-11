import dal.IUserDAO;
import dal.UserDAONonPersistent;
import dal.UserDAOSQL;
import dto.UserDTO;
import func.Func;
import func.IFunc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IUserDAO.DALException {
        Scanner in = new Scanner(System.in);
        IUserDAO dao = new UserDAONonPersistent();
        IFunc func = new Func(dao);
        CLI cli = new CLI(func, in);
        cli.run();
    }
}
