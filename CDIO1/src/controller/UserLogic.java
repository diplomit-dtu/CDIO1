package controller;

import dal.IUserDAO;
import functionality.IFunctionality;

public class UserLogic {
    
    private IUserDAO d;
    private IFunctionality f;
    
    public UserLogic(IUserDAO d, IFunctionality f){
        this.d = d;
        this.f = f;
    }
    
    public void start(){
        //Logic
    }

}
