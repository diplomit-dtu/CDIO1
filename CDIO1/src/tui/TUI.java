package tui;

import dal.UserDAO;
import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class TUI {
    
    private Scanner scan = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int showMenu(String message, String... menuItems){
        System.out.println("#########################################");
        System.out.println(message);
        
        //Display menu
        for (int i = 1; i < menuItems.length+1; i++) {
            System.out.println(i + ". " + menuItems[i-1]);
        }
        
        int choice = 0;
        
        //Until user correctly chooses item from menu
        while(true){
            choice = inputNumber();
            
            if(choice < 1 || choice > menuItems.length+1)
                System.out.println("Indtast venligst et nummer fra listen");
            else
                break;
        }
        
        return choice;
    }
    
    //Gets a number from the user
    private int inputNumber(){
        System.out.println("#########################################");
            int choice;
        
            while(true){
                try{
                    choice = Integer.parseInt(scan.nextLine());
                    break;
                } catch(NumberFormatException e){
                    System.out.println("Indtast venligst et tal");
                }
            }
            
            return choice;
    }

    public String inputName() {
        String name = "";
        do {
            System.out.println("Indtast Navn: ");
            name = scan.nextLine();
            if (name.equals("")){
                System.out.println("Brugeren skal have et navn");
            }
        } while (name.equals(""));
        return name;
    }

    public String inputInit() {
        String init = "";
        boolean check = false;
        do {
            System.out.println("Indtast Initial: ");
            init = scan.nextLine();
            if (init.length() < 2 || init.length() > 4) {
                System.out.println("Initialer skal have mellem 2 - 4 bogstaver");
            } else {
                check = true;
            }
        } while (!check);
        return init;
    }

    public void addRolesToUser(UserDTO user) {
        String inp = "";
        System.out.println("Vælg Roler (skriv tallene for alle de roller brugeren skal have på samme linje):");
        System.out.println("1:Admin, 2:Pharmacist, 3:Foreman, 4:Operator");
        inp = scan.nextLine();
        for (int i = 0; i < inp.length(); i++) { // Will not be able to have more than 10 different roles this way.
            try {
                int x = Character.getNumericValue(inp.charAt(i));
                switch (x) {
                    case 1:
                        user.addRole("Admin");
                        break;
                    case 2:
                        user.addRole("Pharmacist");
                        break;
                    case 3:
                        user.addRole("Foreman");
                        break;
                    case 4:
                        user.addRole("Operator");
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {

            }
        }
    }

    public UserDTO createUser() {

        UserDTO newuser = new UserDTO();

        String name = inputName();
        newuser.setUserName(name);

        String init = inputInit();
        newuser.setIni(init);

        addRolesToUser(newuser);

        newuser.setUserId(UserDTO.getCounter());

        UserDTO.setCounter(UserDTO.getCounter()+1);

        return newuser;
    }

    public void listUsers(List<UserDTO> list){
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
    public int getUserID(){
        System.out.println("Indtast ID: ");

        int id = scan.nextInt();
        scan.nextLine();
        return id;

    }
    
    public String outputString(String message){
        System.out.println(message);
        return scan.nextLine();
    }
    
    public String inputString(String message){
        System.out.println(message);
        return scan.nextLine();
    }
    
}
