package ui;
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
		System.out.println("Welcome to Nameless Program! Please press a button to start:");
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
			case 1: 
				f.getUser();
				System.out.println("1");
				break;
			case 2:
				f.getUserList();
				System.out.println("2");
				break;
			case 3:
				f.createUser();
				System.out.println("3");
				break;
			case 4:
				f.updateUser();
				System.out.println("4");
				break;
			case 5:
				f.deleteUser();
				System.out.println("5");
				break;
			case 6:
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

}
