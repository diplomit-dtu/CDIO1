package functionality;

import java.util.List;

import dal.IUserDAO;
import data.UserDTO;

public interface IFunctionality {
	String[][] getUsers();
	String[] getUser(int userid);
	boolean createUser(String name, String cpr, String password, List<String> roles);
	boolean updateUserName(int userid, String name);
	boolean updatePassword(int userid, String password);
	boolean updateCPR(int userid, String cpr);
	boolean updateRoles(int userid, List<String> roles);
	boolean deleteUser(int userid);
}
