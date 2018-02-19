package main;

import data.IUserDAO;
import data.UserDAO;
import func.FuncImpl;
import func.IFuncImpl;
import ui.IUI;
import ui.TUI;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IUserDAO d = new UserDAO();
		IFuncImpl f = new FuncImpl(d);
		IUI i = new TUI(f);
		i.run();
	}

}
