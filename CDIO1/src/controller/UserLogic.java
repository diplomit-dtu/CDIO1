package controller;

import dal.IUserDAO;
import dto.UserDTO;
import functionality.IFunctionality;
import tui.TUI;

public class UserLogic {
    
    private TUI t;
    private IFunctionality f;
    private IUserDAO d;
    
    public UserLogic(TUI t, IFunctionality f, IUserDAO d){
        this.t = t;
        this.f = f;
        this.d = d;
    }
    
    public void start(){
        
        int choice;
        
        outer:
        while(true){
            
            choice = t.showMenu("Vælg et menupunkt", "Opret ny bruger", "List brugere", "Ret bruger","Slet bruger", "Afslut program");
    
            switch (choice){
                case 1:
                    createUser();
                    break;
                case 2:
                    ListUsers();
                    break;
                case 3:
                    editUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    java.lang.System.exit(0); //Will exit the program with error code 0
                    //break outer;
            }
        }
    }
    
    private void createUser(){
        try {
            d.createUser(t.createUser());
        }catch (IUserDAO.DALException e){
            System.out.println(e.getMessage() + "\n");
        }
    }
    
    private void ListUsers(){
        try {
            t.listUsers(d.getUserList());
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
    
    private void editUser(){
    
        int id = t.getUserID();
        
        try{
            validateID(id);
            UserDTO userDTO = d.getUser(id);
            
            int choice = t.showMenu("Vælg hvad du vil redigere", "Navn", "Brugernavn", "Kodeord", "Roller");
            
            switch (choice){
                case 1:
                    String newName = t.inputName();
                    userDTO.setUserName(newName);
                    break;
                case 2:
                    String newIni = t.inputInit();
                    userDTO.setIni(newIni);
                    break;
                case 3:
                    try{
                        String newPassword = t.inputString("Skriv nyt kodeord: ");
                        f.verifyPassword(newPassword);
                        userDTO.setPassword(newPassword);
                        
                    }catch(Exception e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 4:
                    t.addRolesToUser(userDTO);
                    break;
            }
            
        } catch(userIDNotFound e){
            System.out.println(e.getMessage() + "\n");
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
    
    private void deleteUser(){
       int id = t.getUserID();
       
       try{
           validateID(id);
           d.deleteUser(id);
       } catch (userIDNotFound e){
           System.out.println(e.getMessage() + "\n");
       }catch (IUserDAO.DALException e){
           System.out.println(e.getMessage() + "\n");
       }
    }
    
    private void validateID(int ID) throws userIDNotFound{
        try {
            int[] IDs = f.getUserIDs(d.getUserList());
            if( !(f.isUserIDPresent(ID, IDs)) )
                throw new userIDNotFound("ID ikke fundet");
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }
    
    public class userIDNotFound extends Exception {
        public userIDNotFound(String msg) { super(msg);}
    }
    
    
}
