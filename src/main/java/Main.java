import dal.IUserDAO;
import dal.UserDAONonPersistent;

import dal.UserDAOSQL;
import func.Func;
import func.IFunc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IUserDAO.DALException {
        Scanner in = new Scanner(System.in);
        //LOG IN FOR LOCALHOST
        System.out.println("Type in your SQL username and password on a separate line");
        IUserDAO dao = new UserDAOSQL("localhost","3306","notDatabase",in.nextLine(),in.nextLine());
        dao.getUserList();
        IFunc func = new Func(dao);
        CLI cli = new CLI(func, in);
        cli.run();
    }
}
