package ui;

import java.util.Scanner;

import functionality.Functionality;
import functionality.IFunctionality;

public class TUI {
	private IFunctionality functionality;
	public TUI(IFunctionality functionality) {
		this.functionality = functionality;
	}
	
	
	public void start() {
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Indtast et tal 1-5");
			System.out.println("1. Opret bruger");
			System.out.println("2. Vis brugere");
			System.out.println("3. Ret bruger");
			System.out.println("4. Slet bruger");
			System.out.println("5. Afslut");
			int i = sc.nextInt();
		} while (running);
	}
}
