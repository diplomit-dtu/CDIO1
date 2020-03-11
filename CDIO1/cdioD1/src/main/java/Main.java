import func.Func;
import func.IFunc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        IFunc func = new Func(null);
        CLI cli = new CLI(func, in);
        cli.run();
    }
}
