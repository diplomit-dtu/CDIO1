package functionality;

import data.*;
import data.IUserDAO.DALException;

import java.util.List;


public class Functionality implements IFunctionality {

	IUserDAO data;
	
	public static void main (String[] args) {
		try {
			System.out.println(initials("Jeppe Trip Kofoed"));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
		/*
		 * Creation of this object should probably be handled in the data layer.
		 */

		try {
			UserDTO user = new UserDTO();
			
			user.setUserId(nextID());
			user.setUserName(name);
			user.setIni(initials(name));
			user.setCpr(cpr);
			user.setPassword(password);
			user.setRoles(roles);
			
			data.createUser(user);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	public boolean updateUserName(int userid, String name) {
		UserDTO user;
		
		try {
			user = data.getUser(userid);
			user.setUserName(name);
			
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	public boolean updatePassword(int userid, String password) {
		UserDTO user;
		
		try {
			user = data.getUser(userid);
			user.setPassword(password);
			
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	public boolean updateCPR(int userid, String cpr) {
		UserDTO user;
		
		try {
			user = data.getUser(userid);
			user.setCpr(cpr);
			
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateRoles(int userid, List<String> roles) {
		UserDTO user;
		
		try {
			user = data.getUser(userid);
			user.setRoles(roles);
			
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	public boolean deleteUser(int userid) {
		try {
			data.deleteUser(userid);
			
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	private int nextID() throws DALException {
		int id;
		
		int i = data.getUserList().size();
		id = ++i;
		
		return id; 
	}
	
	//Supposed to return the initials based on the input name.
	private static String initials(String name) throws DALException {
		String[] name_array = name.split(" ");
		char[] char_ini = new char[name_array.length];
		String ini;
		
		for(int i = 0; i<name_array.length; i++) {
				char_ini[i] = name_array[i].charAt(0);
		}

		ini = new String(char_ini);
		
		return ini;
	}

}
