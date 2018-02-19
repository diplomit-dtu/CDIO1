package func;

import java.util.List;

import data.IUserDAO;
import data.UserDTO;

public class FuncImpl implements IFuncImpl{

	IUserDAO d;
	
	public FuncImpl(IUserDAO d)
	{
		this.d = d; 
	}
	
	@Override
	public UserDTO getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserList() {
		// TODO Auto-generated method stub
		return null;
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
	public void deleteUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void createPass() {
		// TODO Auto-generated method stub
	}
}
