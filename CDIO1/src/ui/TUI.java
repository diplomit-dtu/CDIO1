package ui;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import func.IFuncImpl;

public class TUI implements IUI {

	IFuncImpl f;

	public TUI(IFuncImpl f)
	{
		this.f = f;
	}

	@Override
	public void run() {
		System.out.println("Velkommen!");
		System.out.println("1");
		System.out.println("2");
		System.out.println("3");
		System.out.println("4");
		System.out.println("5");
		Scanner sc = new Scanner(System.in);

		boolean hasEnded = false;
		while (!hasEnded) {
			//TODO Fix input to only int
			int selection = sc.nextInt();
			switch (selection) {
			case 1: //getUser
				int userId = sc.nextInt();
				System.out.println("Indtast dit bruger ID: ");
				f.getUser(userId);
				System.out.println("1");
				break;
			case 2: //GetUserList
				f.getUserList();
				System.out.println("2");
				break;
			case 3: //Create user
				System.out.println("Indtast dit burgernavn: ");
				String userName = sc.nextLine();
				System.out.println("Indtast dine initialer: ");
				String ini = sc.nextLine();
				System.out.println("Indtast dit CPR-nummer: ");
				String cpr = sc.nextLine();
				f.createUser(userName, ini, cpr);
				System.out.println("3");
				break;
			case 4: //Update user.
				System.out.println("Indtast dit bruger ID: ");
				int userId = sc.nextInt();
				System.out.println("Hvad vil du ændre?");
				System.out.println("___________________________");
				System.out.println("1: Brugernavn");
				System.out.println("2: Initialer");
				System.out.println("3: Roller");
				System.out.println("4: CPR");
				System.out.println("5: Kodeord");

				int selection2 = sc.nextInt();
				switch(selection2){
				case 1: //Username
					break;
				case 2: //Initails
					break;
				case 3: //Choose roles
					chooseRoles();
					break;
				case 4: //CPR number
					break;
				case 5: //Password
					break;
				default:
					break;
				}
				f.updateUser();
				System.out.println("4");
				break;
			case 5: //Delete user
				int userId = sc.nextInt();
				System.out.println("Indtast dit bruger ID: ");
				f.deleteUser(userId);
				System.out.println("5");
				break;
			case 6: //Exit
				f.exit();
				hasEnded = true;
				System.out.println("6");
				break;
			default: 
				System.out.println("Default");
				break;
			}
		}
	}

	@Override
	public void scenario() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createUser() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getUser() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getUserList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub

	}
	public String chooseRoles() {
		List<String> roller = new ArrayList<String>();
		boolean run = true;
		String st;
		while (run) {
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			switch(input) {
			case 1: //Admin
				st = "Admin";
				if(roller.contains(st))
					System.out.println("Allerede tilføjet");
				else roller.add(st);
				break;
			case 2: //Foreman
				st = "Foreman";
				if(roller.contains(st))
					System.out.println("Allerede tilføjet");
				else roller.add(st);
				break;
			case 3: //Pharmacist
				st = "Pharmacist";
				if(roller.contains(st))
					System.out.println("Allerede tilføjet");
				else roller.add(st);
				break;
			case 4: //Operator
				st = "Operator";
				if(roller.contains(st))
					System.out.println("Allerede tilføjet");
				else roller.add(st);
				break;
			case 5: //Exit
				//TODO exit code.
				run = false;
				break;
			default:
			}

		}
		return null;
	}
}
