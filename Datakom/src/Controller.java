public class Controller {
    
    TUI tui;
    
    public Controller(TUI tui){
        this.tui = tui;
    }
    
    public void start(){
        
        outer:
        while(true){
            int choice = tui.showMenu("Vælg et menupunkt", "Skriv mail", "Afslut");
            System.out.println();
    
            switch(choice){
                case 1:
                    sendMail();
                    break;
                case 2:
                    break outer;
            }
            System.out.println();
        }
        System.out.println("Programmet lukkes...");
    }
    
    public void sendMail(){
        System.out.println("Her");
    }
    
}
