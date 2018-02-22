package func;

import java.util.List;

import data.IUserDAO;
import data.IUserDAO.DALException;
import data.UserDTO;

public class FuncImpl implements IFuncImpl{

	IUserDAO d;
	UserDTO dt;
	
	public FuncImpl(IUserDAO d)
	{
		this.d = d; 
	}
	
	@Override
	public UserDTO getUser(int userId) throws DALException {
		UserDTO user = this.d.getUser(userId);
		return user;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> list = this.d.getUserList();
		return list;
	}

	@Override
	public void createUser(int userId, String userName, String ini, List<String> roles1, String cpr) throws DALException {
		// Creates a UserDTO and sets the attributes to the corresponding parameters.
		// Roles is not implemented yet.
		this.dt = new UserDTO();    
		this.dt.setUserId(userId);
		this.dt.setUserName(userName);
		this.dt.setIni(ini);
		List<String> roles = roles1;
		this.dt.setCpr(cpr);
		String password = createPass();
		this.dt.setPassword(password);
		this.d.createUser(this.dt);
	}

	@Override
	public void updateUser(int userId, String userName, String ini, String roles, String cpr, String password) throws DALException {
		// Updates the user by getting the user and then set the users attributes to the corresponding parameters.
		// Roles is not implemented yet.
		UserDTO user = getUser(userId);
		if(user != null)
		{
			user.setUserId(userId);   
			user.setUserName(userName);              
			user.setIni(ini);
			List<String> roles = roles1;
			user.setCpr(cpr);
			user.setPassword(password);
			this.d.updateUser(user);	
		}
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		// TODO Auto-generated method stub
		// Deletes the user by getting the user and if the user exists then it calls the deleteUser method of the UserDBDAO.
		UserDTO user = getUser(userId);
		if(user != null)
		{
			this.d.deleteUser(userId);	
		}
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String createPass() {
		String pass = "test";
		
		return pass; 
		
	}
	
	//tostring
}
