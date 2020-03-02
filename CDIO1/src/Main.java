import dal.IUserDAO;
import dal.UserDAO;
import tui.TUI;
import passwordVerifier.PasswordVerifier;
import controller.UserLogic;
import functionality.Functionality;

public class Main {
    
    public static void main(String[] args){
        TUI tui = new TUI();
        Functionality f = new Functionality();
        UserDAO d = new UserDAO();
        UserLogic con = new UserLogic(tui, f, d);
        con.start();
        
        //TUI tui = new TUI();
        tui.showMenu("Vælg et menupunkt", "Opret bruger", "Slet bruger", "Rediger bruger", "Oversigt", "Afslut program");


        //password testing... -Completed :-) 
        PasswordVerifier verifier = new PasswordVerifier();
        try {
            if (verifier.verify(tui.inputString("Enter password"))) {
                tui.showMessage("Password accepteret");
            }
        }
        catch (Exception e) {
            tui.showMessage(e.getMessage());
        }
    
    }
    
}
