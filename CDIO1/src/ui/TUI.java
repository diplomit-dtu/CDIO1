package ui;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.IUserDAO.DALException;
import func.IFuncImpl;

public class TUI implements IUI {

	IFuncImpl f;

	public TUI(IFuncImpl f)
	{
		this.f = f;
	}

	@Override
	public void run() throws DALException {
		System.out.println("Velkommen!");
		System.out.println("1"); 	//Get User
		System.out.println("2");		//Get UserList
		System.out.println("3");		//Create user
		System.out.println("4");		//Update user
		System.out.println("5");		//Delete user
		System.out.println("6");		//Exit program
		Scanner sc = new Scanner(System.in);

		boolean hasEnded = false;
		while (!hasEnded) {
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
				System.out.println("Bruger oprettes...");
				System.out.println("Indtast dit burgernavn: ");
				String userName3 = sc.nextLine();
				System.out.println("Indtast dine initialer: ");
				String ini3 = sc.nextLine();
				System.out.println("Indtast dit CPR-nummer: ");
				String roles3 = sc.nextLine();
				System.out.println("Indtast din rolle: ");
				String cpr3 = sc.nextLine();
				f.createUser(userName3, ini3, roles3, cpr3);
				System.out.println("3");
				break;
			case 4: //Update user.
				System.out.println("Indtast dit bruger ID: ");
				int userId4 = sc.nextInt();
				f.getUser(userId4);
				System.out.println("Hvad vil du ændre?");
				System.out.println("___________________________");
				System.out.println("1: Brugernavn");
				System.out.println("2: Initialer");
				System.out.println("3: Roller");
				System.out.println("4: CPR");
//				System.out.println("5: Kodeord");

				int selection2 = sc.nextInt();
				switch(selection2){
				case 1: //Username
					String userName4 = sc.nextLine();
					break;
				case 2: //Initails
					String ini4 = sc.nextLine();
					break;
				case 3: //Choose roles
//					String roles4 = sc.nextLine();
					break;
				case 4: //CPR number
					String cpr4 = sc.nextLine();
					break;
//				case 5: //Password
//					break;
				default:
					break;
					f.updateUser(userId4, userName4, ini4, roles4, cpr4);
				}

				System.out.println("4");
				break;
			case 5: //Delete user
				int userId5 = sc.nextInt();
				System.out.println("Indtast dit bruger ID: ");
				f.deleteUser(userId5);
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

	}

	@Override
	public void createUser() {

	}

	@Override
	public void updateUser() {

	}

	@Override
	public void getUser() {

	}

	@Override
	public void getUserList() {

	}

	@Override
	public void deleteUser() {

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
