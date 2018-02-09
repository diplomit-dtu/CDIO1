package functionality;

import data.IUserDAO;
import data.IUserDAO.DALException;

import java.util.List;


public class Functionality implements IFunctionality {

	IUserDAO data;
	
	public Functionality (IUserDAO data) {
		this.data = data;
	}
	
	@Override
	public String[][] getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getUser(int userid) {
		String[] userArray = new String[7];

		try {
			userArray[0] = ""+data.getUser(userid).getUserId();
			userArray[1] = ""+data.getUser(userid).getUserName();
			userArray[2] = ""+data.getUser(userid).getIni();
			userArray[3] = ""+data.getUser(userid).getCpr();
			userArray[4] = ""+data.getUser(userid).getPassword();
			userArray[5] = ""+data.getUser(userid).getActive();
			userArray[6] = ""+data.getUser(userid).getRoles();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userArray;
	}

	@Override
	public boolean createUser(String name, String cpr, String password, List<String> roles) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserName(int userid, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(int userid, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCPR(int userid, String cpr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRoles(int userid, List<String> roles) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(int userid) {
		// TODO Auto-generated method stub
		return false;
	}

}
