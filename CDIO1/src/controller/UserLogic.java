package controller;

import dal.IUserDAO;
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
                    break outer;
            }
        }
    }
    
    private void createUser(){
    }
    
    private void ListUsers(){
        
        try {
            d.getUserList();
            
            
            
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        
    }
    
    private void editUser(){
    
    }
    
    private void deleteUser(){
    
    }

}
