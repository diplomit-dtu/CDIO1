package data;

import java.util.List;

import data.IUserDAO;

public class UserDBDAO implements IUserDAO{

	@Override
	public UserDTO getUser(int userId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		conn.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
		"INSERT INTO personer(userID, userName, ini, roles, cpr, passwd) VALUES " + 
		"(" + user.getUserId() + ", '" + user.getUserName() + "', '" + user.getIni() + 
		"', '" + user.getRoles() + "', '" + user.getCpr() + "', '" + user.getPassword() + "');"
		);
		
		
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		conn.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
				"UPDATE personer SET " + 
				 "userID = '" + user.getUserId() + "', userName = '" + user.getUserName() + "', ini = '" + user.getIni() + "', " + 
				"roles = '" + user.getRoles() + "', cpr ='" + user.getCpr() + "', passwd = '" + user.getPassword() + "'" + 
				"WHERE userID = " + user.getUserId() + ";"
				);
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		// TODO Auto-generated method stub
		conn.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
				"DELETE FROM personer WHERE userID = " + userId + ";"
				);
	}
}