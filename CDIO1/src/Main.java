import data.PersistentUserDAO;
import functionality.Functionality;
import ui.TUI;

public class Main {
	public static void main(String[] args) {
		//PersistentUserDAO d = new PersistentUserDAO();
		Functionality f = new Functionality();
		TUI ui = new TUI(f);
		ui.start();
	}
}
