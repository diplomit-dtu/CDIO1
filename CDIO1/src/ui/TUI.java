package ui;
import java.util.ArrayList;
import java.util.Arrays;
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
	public void run() throws DALException { //Displays welcome screen 
		System.out.println("Velkommen! Tryk på det tal, der svarer til det menupunkt du gerne vil tilgå");
		System.out.println("1: Find bruger"); 			//Get User
		System.out.println("2: Udskriv alle brugere");	//Get UserList
		System.out.println("3: Opret bruger");			//Create user
		System.out.println("4: Rediger bruger");			//Update user
		System.out.println("5: Slet bruger");			//Delete user
		System.out.println("6: Afslut program");			//Exit program
		Scanner sc = new Scanner(System.in);

		boolean hasEnded = false;
		while (!hasEnded) {
			int selection = sc.nextInt();
			switch (selection) {
			case 1: //getUser
				System.out.println("Indtast bruger ID'et på den bruger du gerne vil finde: ");
				int userId = sc.nextInt();
				f.getUser(userId);
				System.out.println("1"); //Only for test purposes
				break;
			case 2: //GetUserList
				System.out.println("Printer liste...");
				f.getUserList();
				System.out.println("2");	//Only for test purposes
				break;
			case 3: //Create user
				System.out.println("Bruger oprettes...");
				System.out.println("Indtast dit burgernavn: ");
				String userName3 = sc.next();
				System.out.println("Indtast dine initialer: ");
				String ini3 = sc.next();
				System.out.println("Indtast din rolle(r), hvis du har flere så seperer med komma og mellemrum: ");
				String roles3 = sc.next();
				List<String> roles = Arrays.asList(roles3.split(", "));
				System.out.println("Indtast dit CPR-nummer: ");
				String cpr3 = sc.next();
				f.createUser(userName3, ini3, roles, cpr3);	//Creates a user with the inputted information
				System.out.println("3");	//Only for test purposes
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
				
//				Setup for new switch case in update user.
				int selection2 = sc.nextInt();
				String userName4 = null;
				String ini4 = null;
				String cpr4 = null;
				List<String> roles1 = null;
				switch(selection2){		//Switch for the options 1 to 4 above.
				case 1: //Username
					userName4 = sc.nextLine();
					break;
				case 2: //Initails
					ini4 = sc.nextLine();
					break;
				case 3: //Choose roles
					String roles4 = sc.nextLine();
					roles1 = Arrays.asList(roles4.split(", "));
					break;
				case 4: //CPR number
					cpr4 = sc.nextLine();
					break;
//				case 5: //Password
//					break;
				default:
					break;
				}
				f.updateUser(userId4, userName4, ini4, roles1, cpr4);

				System.out.println("4");	//Only for test purposes
				break;
			case 5: //Delete user
				System.out.println("Indtast bruger ID'et på den bruger du gerne vil slette: ");
				int userId5 = sc.nextInt();
				f.deleteUser(userId5);
				System.out.println("5");	//Only for test purposes
				break;
			case 6: //Exit
				System.out.println("Lukker ned...");
				System.out.println("Farvel");

				f.exit();
				hasEnded = true;
				System.out.println("6");	//Only for test purposes
				break;
			default: 
				System.out.println("Default");	//Only for test purposes
				break;
			}
		}
		sc.close();
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
			sc.close();
		}
		return null;
	}
	public void menuptions() {		//Shows the main menu. Use at the end of a switch case.
		System.out.println("1: Find bruger"); 			//Get User
		System.out.println("2: Udskriv alle brugere");	//Get UserList
		System.out.println("3: Opret bruger");			//Create user
		System.out.println("4: Rediger bruger");			//Update user
		System.out.println("5: Slet bruger");			//Delete user
		System.out.println("6: Afslut program");			//Exit program
	}
}