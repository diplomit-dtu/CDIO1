package tui;

import java.util.Scanner;

public class TUI {
    
    private Scanner scan = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
    }
    
    public int showMenu(String message, String... menuItems){
        
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


    public String inputString(String message){
        System.out.println(message);
        return scan.nextLine();
    }
}
