package func;

import java.util.List;

import data.UserDTO;
import data.IUserDAO.DALException;

public interface IFuncImpl {
	UserDTO getUser(); //return objekt and use toString in tui
	List<UserDTO> getUserList(); //same concept
	void createUser(); //create userobjekt in funcImpl and ask for neccesary data
	void updateUser(); // same concept
	void deleteUser(); //same concept
	void exit(); //Shuts down program
	void createPass();
}
