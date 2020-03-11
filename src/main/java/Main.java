import dal.IUserDAO;
import dal.UserDAONonPersistent;
import func.Func;
import func.IFunc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        IUserDAO dao = new UserDAONonPersistent();
        IFunc func = new Func(dao);
        CLI cli = new CLI(func, in);
        cli.run();
    }
}
