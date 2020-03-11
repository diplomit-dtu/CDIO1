import func.IFunc;

import java.util.List;
import java.util.Scanner;

public class CLI{
    final IFunc func;
    final Scanner in;

    public CLI(IFunc func, Scanner scanner){
        this.func = func;
        this.in = scanner;
    }

    // Starts the commandline program
    void run(){
        System.out.println(screen0Mainmenu());
    }

    String screen0Mainmenu(){
        StringBuilder acc = new StringBuilder();
        acc.append("1) Hovedmenu \n");
        acc.append("2) List brugere \n");
        acc.append("3) Opdater bruger \n");
        acc.append("4) Slet bruger \n");
        acc.append("5) Afslut program \n");
        return acc.toString();
    }
    String promptInput(){
        return "Indtast valgmulighed: ";
    }

    int getInput(List<Integer> validChoices){
        int choice = -1;
        choice = in.nextInt();

        if(!validChoices.contains(choice)){
            return -1;
        }
        return choice;
    }
}
