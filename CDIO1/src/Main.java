import dal.IUserDAO;
import dal.UserDAO;
import tui.TUI;
import passwordVerifier.PasswordVerifier;

public class Main {
    
    public static void main(String[] args) {
        
        TUI tui = new TUI();
        tui.showMenu("Vælg et menupunkt", "Opret bruger", "Slet bruger", "Rediger bruger");

        PasswordVerifier verifier = new PasswordVerifier();


    
    }
    
}
