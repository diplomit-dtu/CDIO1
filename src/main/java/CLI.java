import dal.IUserDAO;
//import dal.UserDAOSQL;
import dto.UserDTO;
import func.IFunc;

import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CLI{
    final IFunc func;
    final Scanner in;

    public CLI(IFunc func, Scanner scanner){
        this.func = func;
        this.in = scanner;
    }

    // Starts the commandline program
    void run() throws InterruptedException{
        mainMenu0();
    }

    void mainMenu0() throws InterruptedException{
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
            createUser1();
        }else if(input == 2){
            listUsers2();
        }else if(input == 3){
            updateUser3();
        }else if(input == 4){
            deleteUser4();
        }else if(input == 5){
            System.out.println("Lukker ned...");
            TimeUnit.SECONDS.sleep(1);
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

    void createUser1() throws InterruptedException {
        int id = -1;
        String input;

        System.out.println("Indtast unikt ID (11-99) (skriv STOP for at afbryde): ");
        while(id == -1){
            input = in.nextLine().trim();
            if(input.equals("STOP")){
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
        input = in.nextLine().trim();
        if(input.equals("STOP")){
            return;
        }
        String userName = input;

        System.out.println("Indtast CPR (skriv STOP for at afbryde): ");
        input = in.nextLine().trim();
        if(input.equals("STOP")){
            return;
        }
        String cpr = input;

        // Assign roles
        int choice = -1;
        boolean admin=false;
        boolean farma=false;
        boolean formand=false;
        boolean operator=false;
        while(choice != 5){
            choice = -1;
            System.out.println("1) Administratør" + " " +isSelected(admin));
            System.out.println("2) Farmaceut" + " " + isSelected(farma));
            System.out.println("3) Formand " + " " +  isSelected(formand));
            System.out.println("4) Operatør" + " " +  isSelected(operator));
            System.out.println("5) Gem rolletildeling og opret bruger");
            choice = getInput(Arrays.asList(1,2,3,4,5));
            if(choice == -1){
                System.out.println("Ikke korrekt input, prøv igen: ");
                continue;
            }
            if(choice == 1){
                admin=!admin;
            }else if(choice == 2){
                farma=!farma;
            }else if(choice == 3){
                formand = !formand;
            }else if(choice == 4){
                operator = !operator;
            }
        }
        List<String> roles = new ArrayList<>();
        if(admin){
            roles.add("Administrator");
        }
        if(farma){
            roles.add("Farmaceut");
        }
        if(formand){
            roles.add("Formand");
        }
        if(operator){
            roles.add("Operatør");
        }
        try {
            func.createUser(id, userName, cpr, roles);
        }catch(IFunc.UserFormatException e){
            System.out.println("En eller flere parametre er ukorrekt formateret:");
            if(e.errorlist.contains(IFunc.UserFormatException.errortypes.CPR)){
                System.out.println("- CPR er ukorrekt formateret");
            }
            if(e.errorlist.contains(IFunc.UserFormatException.errortypes.ID)){
                System.out.println("- ID er ukorrekt formateret");
            }
            if(e.errorlist.contains(IFunc.UserFormatException.errortypes.roles)){
                System.out.println("- Rolleangivelse er ukorrekt formateret");
            }
            if(e.errorlist.contains(IFunc.UserFormatException.errortypes.username)){
                System.out.println("- Brugernavn er ukorrekt formateret");
            }
            if(e.errorlist.contains(IFunc.UserFormatException.errortypes.password)){
                System.out.println("- Kodeord er ukorrekt formateret");
            }
            TimeUnit.SECONDS.sleep(2);
        }catch(IFunc.DatabaseException e){
            System.out.println("Enten er der ikke en database, eller brugeren er identisk med et andet element i databasen. Prøv igen");
            TimeUnit.SECONDS.sleep(1);
        }

    }

    String isSelected(boolean b){
        if(b){
            return "✓";
        }else{
            return "x";
        }
    }


    //TODO: Not done yet! Sincerely Christoffer
    // ListUsers2 should use printTable and getUserRows in order to make the proper rows of the format:
    //+-------+------+------+
    //| att1  | att2 | att3 |
    //+-------+------+------+
    void listUsers2(){

    }
    void printTable(List<String> attributes, List<List<String>> rows){
        if (attributes.size() == rows.get(0).size()){
            throw new AssertionError("The number of attributes should always be equal to the row size");
        }
        List<Integer> attWidth = new ArrayList<>();
        for(int i = 0; i<attributes.size(); ++i){
            attWidth.add(attributes.get(i).length());
        }
        for(int r = 0; r<rows.size();++r){
            for(int elem = 0; elem<rows.get(r).size(); ++elem){
                if(attWidth.get(elem)<rows.get(r).get(elem).length()){
                    attWidth.set(elem, rows.get(r).get(elem).length());
                }
            }
        }

        StringBuilder firstLine = new StringBuilder();
        firstLine.append("+");
        for(int i = 0; i<attWidth.size(); ++i){
            firstLine.append(repeatChar("-",attWidth.get(i)+2));
            firstLine.append("+");
        }

        System.out.println(firstLine.toString());
    }
    List<List<String>> getUserRows(){
        List<UserDTO> list = new ArrayList<>();
        try {
            list = func.getUserList();
        }catch(IFunc.DatabaseException e){
            //TODO: better message
            System.out.println(e.getMessage());
        }
        List<List<String>> rows = new ArrayList<>();
        for(int i = 0; i<list.size(); ++i){
            List<String> tmp = new ArrayList<>();
            tmp.add(new Integer(list.get(i).getUserId()).toString());
            tmp.add(list.get(i).getUserName());
            tmp.add(list.get(i).getIni());
            tmp.add(list.get(i).getCpr());
            tmp.add(list.get(i).getPassword());
            StringBuilder acc = new StringBuilder();
            for(int j = 0; j<list.get(i).getRoles().size();++j){
                if(j==0){
                    acc.append(list.get(i).getRoles().get(j));
                }else{
                    acc.append(" og "+list.get(i).getRoles().get(j));
                }
            }
            tmp.add(acc.toString());
            rows.add(tmp);
        }
        return rows;
    }

    String repeatChar(String c,int rep){
        StringBuilder acc = new StringBuilder();
        for(int i = 0; i<rep; ++i) {
            acc.append(c);
        }
        return c.toString();
    }

    //TODO: This needs to be redone so that it actually follows our requirements given on the website Alexander.
    // This includes bringing it back to the Update page when a setting has been made,
    // and _not_ bring it to main menu.
    // It also needs to follow the correct output format.
    // It needs an "save and exit to main menu" button
    // Sincerely Christoffer
    void updateUser3() {
        String useless;
        System.out.println("Indtast user ID, som skal Updateres: ");
        int startID = in.nextInt();
        useless = in.nextLine();
        UserDTO user = null;
        try {
            user = func.getUser(startID);
        }catch (IFunc.DatabaseException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Hvilken værdi vil du ændre: ");
        System.out.println("1) ID");
        System.out.println("2) Navn");
        System.out.println("3) Cpr") ;
        System.out.println("4) Password");
        System.out.println("5) Role");
        int choice = getInput(Arrays.asList(1, 2, 3, 4, 5));

        switch (choice){
            case 1:
                System.out.println("Indtast nyt ID:");
                user.setUserId(in.nextInt());
                in.nextLine();
                break;
            case 2:
                System.out.println("Indtast nyt navn:");
                user.setUserName(in.nextLine());
                break;
            case 3:
                System.out.println("Indtast nyt cpr: ");
                user.setUserCpr(in.nextLine());
                break;
            case 4:
                System.out.println("Indtast nyt Password: ");
                user.setPassword(in.nextLine());
                break;
            case 5:
                while(true) {
                    ArrayList<String> userRoles = new ArrayList<String>();
                    System.out.println("Indtast ny role eller exit");
                    System.out.println("Roles kan være: Admin, Pharmacist, Foreman, Operator, exit");
                    String sChoice = getSInput(Arrays.asList("Admin", "Pharmacist", "Foreman", "Operator", "exit"));

                    if(!sChoice.equals("")){
                        if(!(userRoles.contains(sChoice))){
                            userRoles.add(sChoice);
                        }
                    }else if(sChoice.equals("exit")){
                        if(!userRoles.isEmpty()){
                            user.setRoles(userRoles);
                        }
                        break;
                    }
                }
                break;
            default:
                System.out.println("Fejl i Switch");
        }
        try {
            func.deleteUser(startID);
        }catch(IFunc.DatabaseException e){
            System.out.println(e.getMessage());
        }

        try {
            func.createUser(user.getUserId(), user.getUserName(), user.getCpr(), user.getRoles());
        }catch(IFunc.UserFormatException e){
            //TODO: Make better
            System.out.println("WRONG!!!");

        }catch(IFunc.DatabaseException e){
            System.out.println("Wrong");
        }

    }

    //TODO: this needs to follow the website as well Alexander, more specifically to print the user in the
    // correct format as specificed by the website, see userStrFormat method
    // Sincerely Christoffer
    void deleteUser4() {
        System.out.println("Indtast user ID, som skal slettes: ");
        try {
            System.out.println("User: " + func.deleteUser(in.nextInt()) + " er slettet");
        }catch(IFunc.DatabaseException e){
            System.out.println(e.getMessage());
        }
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
            in.nextLine();
        }catch(InputMismatchException e){
            return -1;
        }

        if(!validChoices.contains(choice)){
            return -1;
        }
        return choice;
    }
    String getSInput(List<String> validChoices){
        String choice = "";
        choice = in.nextLine();
        if(!validChoices.contains(choice)){
            return "";
        }
        return choice;
    }
}
