package func;

import java.util.List;

import data.IUserDAO;
import data.IUserDAO.DALException;
import data.UserDTO;

public class FuncImpl implements IFuncImpl{

	IUserDAO d;
	
	public FuncImpl(IUserDAO d)
	{
		this.d = d; 
	}
	
	@Override
	public UserDTO getUser(int userId) throws DALException {
		UserDTO user = d.getUser(userId);
		return user;
	}

	@Override
	public List<UserDTO> getUserList() {
		return null;
	}

	@Override
	public void createUser(String userName, String ini, String roles1, String cpr) {
		int	userId;
		String password = createPass();
	}

	@Override
	public void updateUser(int userId, String userName, String ini, String roles, String cpr) throws DALException {
		UserDTO user = getUser(userId);
		if(user != null)
		{
			
		}
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		d.deleteUser(userId);
	}

	@Override
	public void exit() {
		
	}
	
	@Override
	public String createPass() {
		String pass = "test";
		
		return pass; 
		
	}
	
	//tostring
}
