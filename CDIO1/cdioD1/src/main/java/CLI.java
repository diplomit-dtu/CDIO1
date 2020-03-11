import dal.IUserDAO;
import dto.UserDTO;
import func.IFunc;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.concurrent.TimeUnit;

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
        mainMenu0();
    }

    void mainMenu0(){
        int input = -1;
        while(input == -1){
            System.out.println(screen0Mainmenu());
            System.out.println(promptInput());
            input = getInput(Arrays.asList(1,2,3,4,5));
            if(input == -1){
                System.out.println("Ikke korrekt input, prøv igen.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    System.out.println(e);
                }
                continue;
            }
        }
        if(input == 1){
            try {
                createUser1();
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(input == 2){
            listUsers2();
        }else if(input == 3){
            updateUser3();
        }else if(input == 4){
            deleteUser4();
        }else if(input == 5){
            System.out.println("Lukker ned...");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                System.out.println(e);
            }
            return;
        }
        mainMenu0();
    }

    String screen0Mainmenu(){
        StringBuilder acc = new StringBuilder();
        acc.append("1) Opret bruger \n");
        acc.append("2) List brugere \n");
        acc.append("3) Opdater bruger \n");
        acc.append("4) Slet bruger \n");
        acc.append("5) Afslut program \n");
        return acc.toString();
    }

    void createUser1() throws IUserDAO.DALException {
        int id = -1;
        String input;

        System.out.println("Indtast unikt ID (11-99) (skriv STOP for at afbryde): ");
        while(id == -1){
            input = in.nextLine();
            if(input.strip() == "STOP"){
                return;
            }
            try{
                id = Integer.parseInt(input);
            }catch(NumberFormatException e){
                System.out.println("Ikke korrekt input, prøv igen: ");
                continue;
            }
        }


        System.out.println("Indtast brugernavn (skriv STOP for at afbryde): ");
        input = in.nextLine();
        if(input.strip() == "STOP"){
            return;
        }
        String userName = input;

        System.out.println("Indtast CPR (skriv STOP for at afbryde): ");
        input = in.nextLine();
        if(input.strip() == "STOP"){
            return;
        }
        String cpr = input;

        // Assign roles


    }

    void listUsers2(){

    }

    void updateUser3(){

    }

    void deleteUser4(){

    }
    String userStrFormat(UserDTO user){
        StringBuilder acc = new StringBuilder();
        acc.append("Unikt ID: " + user.getUserId() + "\n");
        acc.append("Brugernavn: " + user.getUserName() + "\n");
        acc.append("Initialer: " + user.getIni() + "\n");
        acc.append("CPR: " + user.getUserCpr() + "\n");
        acc.append("Kodeord: " + user.getPassword() + "\n");
        acc.append("Roller: ");

        for(int i = 0; i<user.getRoles().size(); ++i){
            if ( i == 0){
                acc.append(user.getRoles().get(i));
            }else{
                acc.append(" og " +user.getRoles().get(i));
            }
        }
        return acc.toString();
    }

    String promptInput(){
        return "Indtast valgmulighed: ";
    }
    int getInput(List<Integer> validChoices){
        int choice = -1;
        try{
            choice = in.nextInt();
        }catch(InputMismatchException e){
            return -1;
        }

        if(!validChoices.contains(choice)){
            return -1;
        }
        return choice;
    }
}
