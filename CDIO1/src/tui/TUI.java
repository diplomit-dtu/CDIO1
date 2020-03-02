package tui;

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

    public UserDTO createUser() {

        UserDTO newuser = new UserDTO();

        String name = "";
        do {
            System.out.println("Indtast Navn: ");
            name = scan.nextLine();
            if (name.equals("")){
                System.out.println("Brugeren skal have et navn");
            }
        } while (name.equals(""));
        newuser.setUserName(name);

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
        newuser.setIni(init);

        newuser.getRoles();

        do {
            System.out.println("Indtast Role");
        }
        newuser.addRole(scan.nextLine());
        newuser.setUserId(UserDTO.getCounter());

        UserDTO.setCounter(UserDTO.getCounter()+1);

        return newuser;
    }

    public void listUsers(List<UserDTO> list){
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
    public int deleteUser(){
        System.out.println("Indtast ID: ");

        int id = scan.nextInt();
        return id;

    }
    public String inputString(String message){
        System.out.println(message);
        return scan.nextLine();
    }
}
