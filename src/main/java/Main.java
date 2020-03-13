import dal.IUserDAO;
import dal.UserDAONonPersistent;

//import dal.UserDAOSQL;
import func.Func;
import func.IFunc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        Scanner in = new Scanner(System.in);
        //LOG IN FOR LOCALHOST
        System.out.println("Type in your SQL username and password on a separate line");
        //IUserDAO dao = new UserDAOSQL("localhost","3306","notDatabase",in.nextLine(),in.nextLine());
        IUserDAO dao = new UserDAONonPersistent();
        IFunc func = new Func(dao);
        CLI cli = new CLI(func, in);
        cli.run();
    }
}
